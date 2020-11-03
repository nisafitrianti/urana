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
import org.d3ifcool.urana.data.PemainDB
import org.d3ifcool.urana.databinding.FragmentPermainanBinding
import org.d3ifcool.urana.databinding.FragmentPertanyaanBinding
import org.d3ifcool.urana.viewmodel.PemainViewModel
import org.d3ifcool.urana.viewmodel.PemainViewModelFactory

class PermainanFragment : Fragment() {

    lateinit var binding: FragmentPermainanBinding
    private var turn = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_permainan, container, false)

        arguments.let {
            if (it != null) {
                turn = it.getInt("TURN",0)
            }
        }

        binding.btnPlay.setOnClickListener {
            arguments?.putInt("TURN",turn++)
            view?.findNavController()?.navigate(R.id.action_permainanFragment_to_pertanyaanFragment,arguments)
        }

        binding.btnPoin.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_permainanFragment_to_poinPermainanFragment)
        }

        viewModelPemain.data.observe(viewLifecycleOwner, Observer {
            binding.tvPemain.text = it.get(turn++).name
        })

        return binding.root
    }

    private val viewModelPemain: PemainViewModel by lazy {
        val dataSource = PemainDB.getInstance(requireContext()).dao
        val factory = PemainViewModelFactory(dataSource)
        ViewModelProvider(this, factory).get(PemainViewModel::class.java)
    }

}