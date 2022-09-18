package com.vid.sendingdatafromactivitytofragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.vid.sendingdatafromactivitytofragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val bmiFragment = BMIFragment()

        binding.btnCalc.setOnClickListener {

            val weight = binding.etWeight.text.toString().toInt()
            val height = binding.etHeight.text.toString().toInt()

            val bundle = Bundle()
            bundle.putInt("weight", weight)
            bundle.putInt("height", height)

            bmiFragment.arguments = bundle

            fragmentTransaction.add(R.id.frFragment, bmiFragment)
            fragmentTransaction.commit()



        }
    }
}