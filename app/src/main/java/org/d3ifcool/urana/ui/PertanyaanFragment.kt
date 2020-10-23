package org.d3ifcool.urana.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import org.d3ifcool.urana.R
import org.d3ifcool.urana.data.*
import org.d3ifcool.urana.databinding.FragmentPertanyaanBinding
import org.d3ifcool.urana.viewmodel.PemainViewModel
import org.d3ifcool.urana.viewmodel.PemainViewModelFactory
import org.d3ifcool.urana.viewmodel.QuestionViewModel
import org.d3ifcool.urana.viewmodel.QuestionViewModelFactory

class PertanyaanFragment : Fragment() {

    lateinit var binding: FragmentPertanyaanBinding
    lateinit var listPemain: List<Pemain>
    lateinit var listQuestion: List<QuestionList>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_pertanyaan, container, false)

        viewModelPemain.data.observe(viewLifecycleOwner, Observer {
            listPemain = it
        })

        viewModelQuestion.data.observe(viewLifecycleOwner, Observer {
            listQuestion = it
        })

        var x = (0..listQuestion.size).random()
        binding.tvPertanyaan.text = listQuestion.get(x).question
        binding.tvKategori.text = listQuestion.get(x).category

        binding.btnExit.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_pertanyaanFragment_to_home2)
        }

        return binding.root
    }

    private val viewModelPemain: PemainViewModel by lazy {
        val dataSource = PemainDB.getInstance(requireContext()).dao
        val factory = PemainViewModelFactory(dataSource)
        ViewModelProvider(this, factory).get(PemainViewModel::class.java)
    }

    private val viewModelQuestion: QuestionViewModel by lazy {
        val dataSource = QuestionListDB.getInstance(requireContext()).dao
        val factory = QuestionViewModelFactory(dataSource)
        ViewModelProvider(this, factory).get(QuestionViewModel::class.java)
    }

}