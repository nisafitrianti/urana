package org.d3ifcool.urana.ui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApp
import kotlinx.android.synthetic.main.fragment_play.*
import org.d3ifcool.urana.R
import org.d3ifcool.urana.adapter.PemainAdapter
import org.d3ifcool.urana.data.*
import org.d3ifcool.urana.databinding.FragmentPlayBinding
import org.d3ifcool.urana.viewmodel.PemainViewModel
import org.d3ifcool.urana.viewmodel.PemainViewModelFactory


class PlayFragment : Fragment() {

    lateinit var binding: FragmentPlayBinding
    private lateinit var adapter : PemainAdapter

    private val buttonHandler = object : PemainAdapter.ClickHandler {
        override fun onClick(position: Int, pemain:Pemain) {
            viewModel.delete(pemain)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_play, container, false)
        FirebaseApp.initializeApp(requireContext())

        binding.btnPlay.visibility = View.GONE

        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnBack.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_playFragment_to_home2)
        }

        binding.btnAdd.setOnClickListener {
            val namaPemain = binding.etName.text.toString()

            if (namaPemain.isEmpty()) {
                showMessage(R.string.wajib_diisi)
            }else {
                val dataPemain = Pemain(name = namaPemain, score = 0)
                viewModel.insertData(dataPemain)
                binding.btnPlay.visibility = View.VISIBLE
            }
        }

        adapter = PemainAdapter(buttonHandler)
        binding.recyclerView.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        binding.btnPlay.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_playFragment_to_permainanFragment)
        }

        return binding.root
    }

    private val viewModel: PemainViewModel by lazy {
        val dataSource = PemainDB.getInstance(requireContext()).dao
        val factory =
            PemainViewModelFactory(dataSource)
        ViewModelProvider(this, factory).get(PemainViewModel::class.java)
    }

    private fun showMessage(messageResId: Int) {
        val toast = Toast.makeText(requireContext(), messageResId,
            Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

}