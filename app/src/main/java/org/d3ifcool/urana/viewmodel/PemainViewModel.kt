package org.d3ifcool.urana.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3ifcool.urana.data.Pemain
import org.d3ifcool.urana.data.PemainDao
import org.d3ifcool.urana.data.QuestionList
import org.d3ifcool.urana.data.QuestionListDao

class PemainViewModel(private val db : PemainDao): ViewModel() {

    val data = db.getData()

    fun insertData(pemain: Pemain) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insertData(pemain)
            }
        }
    }

    fun delete(pemain: Pemain){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.delete(pemain)
            }
        }
    }

    fun addSrore(id : Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                var pemain = db.getById(id).get(0)
                pemain.score += 2
                db.update(pemain)
            }
        }
    }

    fun minSrore(id : Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                var pemain = db.getById(id).get(0)
                pemain.score -= 2
                db.update(pemain)
            }
        }
    }

}