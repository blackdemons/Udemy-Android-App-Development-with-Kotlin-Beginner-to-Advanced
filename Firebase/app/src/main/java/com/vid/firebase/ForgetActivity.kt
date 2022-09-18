package com.vid.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.vid.firebase.databinding.ActivityForgetBinding

class ForgetActivity : AppCompatActivity() {
    lateinit var binding: ActivityForgetBinding

    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityForgetBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnReset.setOnClickListener {
            val email = binding.etEmailReset.text.toString()

            auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful){

                    Toast.makeText(applicationContext
                        ,"We sent a password reset mail to your mail address"
                        ,Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}