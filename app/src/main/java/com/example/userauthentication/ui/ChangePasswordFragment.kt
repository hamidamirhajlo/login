package com.example.userauthentication.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.userauthentication.R
import kotlinx.android.synthetic.main.fragment_change_password.*

class ChangePasswordFragment : Fragment(R.layout.fragment_change_password) {

    private var newPassword2 = ""
    private var newPassword = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()

        btnSubmitChangePassword.isEnabled = false

        edtChangePassword.addTextChangedListener {
            newPassword = it.toString()
            btnSubmitChangePassword.isEnabled = (it?.length!! >= 6 && it.toString() == newPassword2)

        }

        edtChangePasswordConfirm.addTextChangedListener {
            newPassword2 = it.toString()
            btnSubmitChangePassword.isEnabled = (it?.length!! >= 6 && it.toString() == newPassword)
        }

        btnSubmitChangePassword.setOnClickListener {

            Toast.makeText(context, "password changed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupToolbar() {
        toolbar.setupWithNavController(
            findNavController(), AppBarConfiguration(findNavController().graph)
        )
    }
}