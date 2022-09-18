package com.vid.userinteractions

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var showToast: Button
    lateinit var showSnackbar: Button
    lateinit var myLayout: ConstraintLayout
    lateinit var showDialogMessage: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showToast = findViewById(R.id.buttonShowToast)
        showSnackbar = findViewById(R.id.buttonShowSnackebar)
        myLayout = findViewById(R.id.myLayout)
        showDialogMessage = findViewById(R.id.buttonDialogMessage)

        showToast.setOnClickListener {
            Toast.makeText(
                applicationContext, "This is a Toast Message.",
                Toast.LENGTH_SHORT
            ).show()
        }

        showSnackbar.setOnClickListener {
            Snackbar.make(
                myLayout,
                "This is a Snackbar Message",
                Snackbar.LENGTH_INDEFINITE
            ).setAction("Close", View.OnClickListener {

            }).show()
        }

        showDialogMessage.setOnClickListener {

            showAlertDialog()

        }
    }

    fun showAlertDialog(){

        var alertDialog = AlertDialog.Builder(this@MainActivity)

        alertDialog.setTitle("Change")
            .setMessage("Do you want to change the text of the button?")
            .setIcon(R.drawable.warning)
            .setCancelable(false)
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->

                dialog.cancel()
            })
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->

                showDialogMessage.text = "Alert Dialog"
            })

        alertDialog.create().show()

    }
}