package org.d3ifcool.urana

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3ifcool.urana.data.QuestionListDao
import javax.sql.CommonDataSource

class AddQuestionViewModelFactory(private val dataSource: QuestionListDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddQuestionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddQuestionViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unable to construct ViewModel")
    }
}