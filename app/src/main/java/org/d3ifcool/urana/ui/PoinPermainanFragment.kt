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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3ifcool.urana.R
import org.d3ifcool.urana.adapter.PemainAdapter
import org.d3ifcool.urana.adapter.PoinPemainAdapter
import org.d3ifcool.urana.data.PemainDB
import org.d3ifcool.urana.databinding.FragmentPoinPermainanBinding
import org.d3ifcool.urana.viewmodel.PemainViewModel
import org.d3ifcool.urana.viewmodel.PemainViewModelFactory

class PoinPermainanFragment : Fragment() {

    lateinit var binding: FragmentPoinPermainanBinding
    private lateinit var adapter : PoinPemainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_poin_permainan, container, false)

        binding.btnBack.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_poinPermainanFragment_to_permainanFragment)
        }

        binding.btnExit.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_poinPermainanFragment_to_menangFragment)
        }

        adapter = PoinPemainAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return binding.root
    }

    private val viewModel: PemainViewModel by lazy {
        val dataSource = PemainDB.getInstance(requireContext()).dao
        val factory =
            PemainViewModelFactory(dataSource)
        ViewModelProvider(this, factory).get(PemainViewModel::class.java)
    }

}