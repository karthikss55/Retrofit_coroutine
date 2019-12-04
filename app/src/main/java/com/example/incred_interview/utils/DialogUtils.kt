package com.example.incred_interview.utils

import android.app.Activity
import androidx.appcompat.app.AlertDialog

class DialogUtils {
    companion object {
        fun displayErrorDialog(activity: Activity, message: String) {
            // Initialize a new instance of
            val builder = AlertDialog.Builder(activity)

            // Set the alert dialog title
            builder.setTitle("Network Error")

            // Display a message on alert dialog
            builder.setMessage(message)

            // Set a positive button and its click listener on alert dialog
            builder.setPositiveButton("OK") { dialog, which ->
                dialog.dismiss()
                if (!activity.isFinishing) {
                    activity.finish()
                }
            }

            builder.setNeutralButton("Retry"){ dialog, which ->
            }
            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
        }
    }
}