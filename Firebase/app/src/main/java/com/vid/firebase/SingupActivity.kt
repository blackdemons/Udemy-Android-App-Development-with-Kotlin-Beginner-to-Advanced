package com.vid.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.vid.firebase.databinding.ActivitySingupBinding

class SingupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySingupBinding
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySingupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSingupUser.setOnClickListener {
            val userEmail = binding.etEmailSingup.text.toString()
            val userPassword = binding.etPasswordSingup.text.toString()

            signupWithFirebase(userEmail,userPassword)
        }
    }

    fun signupWithFirebase(userEmail: String, userPassword: String){
        auth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener { task ->
            if (task.isSuccessful){
                Toast.makeText(applicationContext, "Your account has been created",Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(applicationContext, task.exception?.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }
}