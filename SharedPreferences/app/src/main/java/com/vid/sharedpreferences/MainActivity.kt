package com.vid.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vid.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    var count = 0

    var name: String? = null
    var message: String? = null
    var isChecked: Boolean? = null

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root

        setContentView(view)

        mainBinding.btnCounter.setOnClickListener {
            count++
            mainBinding.btnCounter.text = count.toString()
        }
    }

    override fun onPause() {
        super.onPause()
        saveData()
    }

    fun saveData() {
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)

        name = mainBinding.etName.text.toString()
        message = mainBinding.etMessage.text.toString()
        isChecked = mainBinding.checkBox.isChecked

        var editor = sharedPreferences.edit()

        editor.putString("key name", name)
        editor.putString("key message", message)
        editor.putInt("key count", count)
        editor.putBoolean("key checkBox", isChecked!!)

        editor.apply()

        Toast.makeText(applicationContext, "Your data is save.", Toast.LENGTH_LONG).show()
    }

    fun retreiveData() {
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)

        name = sharedPreferences.getString("key name", null)
        message = sharedPreferences.getString("key message", null)
        count = sharedPreferences.getInt("key count", 0)
        isChecked = sharedPreferences.getBoolean("key checkBox", false)

        mainBinding.etName.setText(name)
        mainBinding.etMessage.setText(message)
        mainBinding.btnCounter.setText("$count")
        mainBinding.checkBox.isChecked = isChecked!!

    }

    override fun onResume() {
        super.onResume()
        retreiveData()
    }
}