package org.d3ifcool.urana.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import org.d3ifcool.urana.R
import org.d3ifcool.urana.databinding.FragmentMenangBinding

class MenangFragment : Fragment() {

    lateinit var binding: FragmentMenangBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_menang, container, false)

        binding.btnPlay.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_menangFragment_to_permainanFragment)
        }

        binding.btnHome.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_menangFragment_to_home2)
        }

        binding.tvPemain.text

        return binding.root
    }

}