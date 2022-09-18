package com.vid.sendingdatafromactivitytofragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vid.sendingdatafromactivitytofragment.databinding.FragmentBMIBinding


class BMIFragment : Fragment() {
    private  var _binding : FragmentBMIBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentBMIBinding.inflate(inflater, container, false)

        val weight = arguments?.getInt("weight")!!.toInt()
        val height = arguments?.getInt("height")!!.toInt()

        val bmi: Double = ((weight * 10000) / (height * height)).toDouble()

        binding.tvFragmentResult.text = "Your BMI is $bmi"

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}