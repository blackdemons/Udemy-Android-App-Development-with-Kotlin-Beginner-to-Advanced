package com.vid.sendingdatafromfragmenttoactivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vid.sendingdatafromfragmenttoactivity.databinding.FragmentMyBinding

class MyFragment : Fragment() {
    private var _binding: FragmentMyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMyBinding.inflate(inflater, container, false)

        binding.btnFragmentSend.setOnClickListener {

            val userName = binding.etFragmName.text.toString()
            val userEmail = binding.etFragmEmail.text.toString()

            (activity as MainActivity).takeData(userName, userEmail)
//            val mainActivity: MainActivity = activity as MainActivity
//            mainActivity.takeData(userName, userEmail)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}