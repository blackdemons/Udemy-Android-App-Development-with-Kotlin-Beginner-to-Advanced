package com.vid.broadcastreciver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BroadcastExample: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val isAirPlaneMode : Boolean = intent!!.getBooleanExtra("state", false)
        if (isAirPlaneMode) {
            Toast.makeText(context, "The device is an airplane mode.", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context, "The device is not in airplane mode.", Toast.LENGTH_LONG).show()
        }
    }
}