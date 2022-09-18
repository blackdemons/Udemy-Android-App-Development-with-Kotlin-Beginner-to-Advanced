package com.vid.dialogfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.vid.dialogfragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnShowDialog.setOnClickListener {

            val fragmentManager: FragmentManager = supportFragmentManager
            val myDialogFragment = MyDialogFragment()

            myDialogFragment.isCancelable = false

            myDialogFragment.show(fragmentManager, "MyDialogFragment" )


        }
    }

    fun getUserData(userName: String, userAge: Int){
        binding.tvActivityName.text = getString(R.string.et_dialogName)+ " :  $userName"
        binding.tvActvityAge.text = getString(R.string.et_dialogAge)+ " :  $userAge"


    }
}