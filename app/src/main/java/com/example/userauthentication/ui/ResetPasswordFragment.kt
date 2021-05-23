package com.example.userauthentication.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.userauthentication.JavaMailAPI
import com.example.userauthentication.R
import com.example.userauthentication.Utils.isValidEmail
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_reset_password.*
import kotlinx.android.synthetic.main.fragment_reset_password.toolbar
import kotlin.random.Random

class ResetPasswordFragment : Fragment(R.layout.fragment_reset_password) {

    private val code = String.format("%06d", Random.nextInt(900000) + 100000)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()

        btnSendResetCode.isEnabled = false

        edtEmailReset.addTextChangedListener {
            btnSendResetCode.isEnabled = it.toString().isValidEmail()
        }

        btnSendResetCode.setOnClickListener {
            sendEmail(edtEmailReset.text.toString())
        }
    }

    private fun sendEmail(mail: String) {
        val jvmAPI = JavaMailAPI(
            requireContext(),
            mail,
            "reset password code is here:",
            code
        )
         jvmAPI.execute()

        jvmAPI.onEmailResult = object :JavaMailAPI.OnEmailResult{
            override fun onResult(result: Boolean) {
                if (result){
                    findNavController().navigate(R.id.action_resetPasswordFragment_to_codeValidationFragment,
                        bundleOf("code" to code))
                }else{
                    Toast.makeText(context, "email not sent", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupToolbar() {
        toolbar.setupWithNavController(
            findNavController(), AppBarConfiguration(findNavController().graph)
        )
    }

}