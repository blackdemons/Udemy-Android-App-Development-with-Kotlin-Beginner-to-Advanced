package com.vid.firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.vid.firebase.databinding.ActivityPhoneBinding
import java.util.concurrent.TimeUnit

class PhoneActivity : AppCompatActivity() {
    lateinit var binding: ActivityPhoneBinding

    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    lateinit var mCallbaks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    var verificationCode = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPhoneBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSendSMSCode.setOnClickListener {
            val userPhoneNumber = binding.etPhoneNumber.text.toString()
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(userPhoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this@PhoneActivity)
                .setCallbacks(mCallbaks)
                .build()

            PhoneAuthProvider.verifyPhoneNumber(options)
        }

        binding.btnVerify.setOnClickListener {
                singinWithSMSCode()

        }

        mCallbaks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                TODO("Not yet implemented")
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                TODO("Not yet implemented")
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                verificationCode = p0
            }
        }
    }

    fun singinWithSMSCode() {

        val userEnterCode = binding.etVerificationCode.text.toString()

        val credential = PhoneAuthProvider.getCredential(verificationCode, userEnterCode)

        singinWithPhoneAuthCredential(credential)
    }

    fun singinWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val intent = Intent(this@PhoneActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(applicationContext,"The code you entres is incorrect", Toast.LENGTH_SHORT).show()
            }

        }
    }
}