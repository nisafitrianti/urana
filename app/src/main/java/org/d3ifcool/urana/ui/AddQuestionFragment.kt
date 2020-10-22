package org.d3ifcool.urana.ui

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import org.d3ifcool.urana.viewmodel.AddQuestionViewModel
import org.d3ifcool.urana.viewmodel.AddQuestionViewModelFactory
import org.d3ifcool.urana.R
import org.d3ifcool.urana.data.QuestionList
import org.d3ifcool.urana.data.QuestionListDB
import org.d3ifcool.urana.databinding.FragmentAddQuestionBinding

/**
 * A simple [Fragment] subclass.
 */
class AddQuestionFragment : Fragment() {

    lateinit var binding: FragmentAddQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_question, container, false)

        binding.btnBack.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_addQuestion_to_home2)
        }

        viewModel.data.observe(viewLifecycleOwner, Observer {
        })

        binding.btnTambahPertanyaan.setOnClickListener {
            val pertanyaan = binding.etPertanyaan.text.toString()
            val kategori = binding.etKategori.text.toString()

            if (pertanyaan.isEmpty() || kategori.isEmpty()) {
                showMessage(R.string.wajib_diisi)
            }else {
                val dataQuestion = QuestionList(question = pertanyaan, category = kategori)
                viewModel.insertData(dataQuestion)
            }
        }

        viewModel.data.observe(viewLifecycleOwner, Observer {
            if (it.size>0) Toast.makeText(requireContext(), "Pertanyaan kamu berhasil di tambahkan", Toast.LENGTH_SHORT).show()
            binding.etPertanyaan.text.clear()
            binding.etKategori.text.clear()
        })

        return binding.root
    }

    private val viewModel: AddQuestionViewModel by lazy {
        val dataSource = QuestionListDB.getInstance(requireContext()).dao
        val factory =
            AddQuestionViewModelFactory(dataSource)
        ViewModelProvider(this, factory).get(AddQuestionViewModel::class.java)
    }

    private fun showMessage(messageResId: Int) {
        val toast = Toast.makeText(requireContext(), messageResId,
            Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}
