package com.example.userauthentication.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.userauthentication.R
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment:Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogout.setOnClickListener {

        }
    }
}