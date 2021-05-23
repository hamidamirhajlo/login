package com.example.userauthentication

import android.text.TextUtils
import android.util.Patterns

object Utils {
    const val EMAIL = "crayanak@gmail.com"
    const val PASSWORD = "hamid3236!"

    fun CharSequence.isValidEmail() =
        !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()


}