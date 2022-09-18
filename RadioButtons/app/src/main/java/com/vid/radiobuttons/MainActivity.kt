package com.vid.radiobuttons

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var linerLayout: LinearLayout
    lateinit var green: RadioButton
    lateinit var yellow: RadioButton
    lateinit var red: RadioButton
    lateinit var change: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linerLayout = findViewById(R.id.linerLayout)
        green = findViewById(R.id.radionButtonGreen)
        yellow = findViewById(R.id.radionButtonYellow)
        red = findViewById(R.id.radionButtonRed)
        change = findViewById(R.id.buttonChange)

        change.setOnClickListener {
            if (green.isChecked) {
                linerLayout.setBackgroundColor(Color.GREEN)
            } else if (yellow.isChecked) {
                linerLayout.setBackgroundColor(Color.YELLOW)
            } else if (red.isChecked) {
                linerLayout.setBackgroundColor(Color.RED)
            }
        }
    }
}