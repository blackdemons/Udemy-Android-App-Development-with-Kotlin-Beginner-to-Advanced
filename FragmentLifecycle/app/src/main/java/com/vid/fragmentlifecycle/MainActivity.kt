package com.vid.fragmentlifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Message", "Main Activity onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Message", "Main Activity onDestroy")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Message", "Main Activity onStop")
    }
    override fun onStart() {
        super.onStart()
        Log.d("Message", "Main Activity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Message", "Main Activity onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Message", "Main Activity onRestart")
    }

}