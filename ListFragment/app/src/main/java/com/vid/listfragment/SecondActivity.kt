package com.vid.listfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.vid.listfragment.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val position = intent.getIntExtra("position", 0)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        val secondFragment = SecondFragment()

        val bundle = Bundle()
        bundle.putInt("position", position)
        secondFragment.arguments = bundle


        fragmentTransaction.add(R.id.frFragment, secondFragment)
        fragmentTransaction.commit()
    }
}