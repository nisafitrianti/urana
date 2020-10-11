package org.d3ifcool.urana.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Entity
data class QuestionList (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val question: String,
    val category: String
)

@Dao
interface QuestionListDao {
    @Insert
    fun insertData(questionList: QuestionList)

    @Query("SELECT * FROM questionlist ORDER BY question")
    fun getData(): LiveData<List<QuestionList>>

    @Query("DELETE FROM questionlist WHERE id IN (:ids)")
    fun deleteData(ids: List<Int>)
}

@Database(entities = [QuestionList::class], version = 1, exportSchema = false)
abstract class QuestionListDB : RoomDatabase() {
    abstract val dao : QuestionListDao
    companion object {
        @Volatile
        private var INSTANCE: QuestionListDB? = null
        fun getInstance(context: Context): QuestionListDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        QuestionListDB::class.java,
                        "questionlist.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}