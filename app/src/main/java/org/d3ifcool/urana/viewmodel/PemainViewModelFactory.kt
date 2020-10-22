package org.d3ifcool.urana.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3ifcool.urana.data.PemainDao
import org.d3ifcool.urana.data.QuestionListDao

class PemainViewModelFactory(private val dataSource: PemainDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PemainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PemainViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unable to construct ViewModel")
    }
}