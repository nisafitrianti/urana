package org.d3ifcool.urana.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.firebase.FirebaseApp
import org.d3ifcool.urana.R
import org.d3ifcool.urana.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)

        binding.btnTentang.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_home2_to_about)
        }

        binding.btnCaraBermain.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_home2_to_howToPlay)
        }

        binding.btnBuatPertanyaan.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_home2_to_addQuestion)
        }

        binding.btnMulaiPermainan.setOnClickListener {
            FirebaseApp.initializeApp(requireContext())
            view?.findNavController()?.navigate(R.id.action_home2_to_playFragment)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

}
