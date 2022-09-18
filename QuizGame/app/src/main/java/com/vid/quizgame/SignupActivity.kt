package com.vid.quizgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.vid.quizgame.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    lateinit var signupBinding: ActivitySignupBinding

    val auth : FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        signupBinding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(signupBinding.root)

        signupBinding.btnSignUp.setOnClickListener {

            val email = signupBinding.etTextSignupEmail.text.toString()
            val password = signupBinding.etTextSignupPassword.text.toString()
            signupWithFirebase(email, password)
        }
    }

    fun signupWithFirebase(email: String, password: String){
        signupBinding.progressBarSignup.visibility = View.VISIBLE
        signupBinding.btnSignUp.isClickable = false

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Toast.makeText(applicationContext, "Your account has been created", Toast.LENGTH_SHORT).show()
                finish()
                signupBinding.progressBarSignup.visibility = View.INVISIBLE
                signupBinding.btnSignUp.isClickable = true

            }else{
                Toast.makeText(applicationContext, task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }

    }
}