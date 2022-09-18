package com.vid.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.vid.fragments.databinding.ActivityMainBinding
import com.vid.fragments.R

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        val firstFragment = FirstFragment()

        fragmentTransaction.add(R.id.frlMain, firstFragment)

        fragmentTransaction.commit()

        binding.btnReplaceFragment.setOnClickListener {
            val secondFragmentManager: FragmentManager = supportFragmentManager
            val secondFragmentTransaction: FragmentTransaction = secondFragmentManager.beginTransaction()
            val secondFragment = SecondFragment()

            secondFragmentTransaction.replace(R.id.frlMain, secondFragment)

            secondFragmentTransaction.addToBackStack(null)

            secondFragmentTransaction.commit()

        }

    }
}