package com.vid.listfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vid.listfragment.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        val position =  arguments?.getInt("position", 0)

        when(position){
            0 -> binding.ivCityImage.setImageResource(R.drawable.berlin)
            1 -> binding.ivCityImage.setImageResource(R.drawable.athens)
            2 -> binding.ivCityImage.setImageResource(R.drawable.rome)
            3 -> binding.ivCityImage.setImageResource(R.drawable.tokyo)
            4 -> binding.ivCityImage.setImageResource(R.drawable.amsterdam)

        }

        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}