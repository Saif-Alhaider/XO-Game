package com.example.xogame.util

import android.content.Context
import android.widget.Toast

fun createToast(context: Context,message:String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG)
        .show()
}