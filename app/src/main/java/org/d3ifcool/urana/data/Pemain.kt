package org.d3ifcool.urana.data

import android.content.Context
import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.room.*

@Entity
data class Pemain (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    var score: Int
)

@Dao
interface PemainDao {
    @Insert
    fun insertData(pemain: Pemain)

    @Update
    fun update(pemain: Pemain)

    @Query("SELECT * FROM Pemain ORDER BY name")
    fun getData(): LiveData<List<Pemain>>

    @Query("DELETE FROM Pemain WHERE id IN (:ids)")
    fun deleteData(ids: List<Int>)

    @Delete
    fun delete(pemain: Pemain)

    @Query("SELECT * FROM Pemain WHERE id = :id")
    fun getById(id: Int) : List<Pemain>

}

@Database(entities = [Pemain::class], version = 1, exportSchema = false)
abstract class PemainDB : RoomDatabase() {
    abstract val dao : PemainDao
    companion object {
        @Volatile
        private var INSTANCE: PemainDB? = null
        fun getInstance(context: Context): PemainDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PemainDB::class.java,
                        "pemain.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}