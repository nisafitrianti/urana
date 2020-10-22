package org.d3ifcool.urana.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3ifcool.urana.data.QuestionList
import org.d3ifcool.urana.data.QuestionListDao

class AddQuestionViewModel(private val db : QuestionListDao): ViewModel() {

    val data = db.getData()

    fun insertData(questionList: QuestionList){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insertData(questionList)
            }
        }
    }

}