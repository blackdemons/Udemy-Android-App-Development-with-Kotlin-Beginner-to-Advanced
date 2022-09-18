package com.vid.sendingemail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vid.sendingemail.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSendEmail.setOnClickListener {
            val userAddress = binding.etEmailAddress.text.toString()
            val userSubject = binding.etEmailSubject.text.toString()
            val userMessage = binding.etEmailMessage.text.toString()

            sendEmail(userAddress, userSubject, userMessage)
        }
    }

    fun sendEmail(userAddress: String, userSubject: String, userMessage: String) {

        val emailAddress = arrayOf(userAddress)

        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddress)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, userSubject)
        emailIntent.putExtra(Intent.EXTRA_TEXT, userMessage)

        if (emailIntent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(emailIntent, "Choose an app"))
        }
    }
}