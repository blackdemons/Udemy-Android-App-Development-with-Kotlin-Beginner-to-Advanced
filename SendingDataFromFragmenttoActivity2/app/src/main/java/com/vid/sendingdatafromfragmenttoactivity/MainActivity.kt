package com.vid.sendingdatafromfragmenttoactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.vid.sendingdatafromfragmenttoactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val myFragment = MyFragment()

        fragmentTransaction.add(R.id.frFragment, myFragment)
        fragmentTransaction.commit()
    }

    fun takeData(userName: String, userEmail: String){
        binding.tvActivName. text = "$userName"
        binding.tvActivEmail.text = "$userEmail"
    }
}