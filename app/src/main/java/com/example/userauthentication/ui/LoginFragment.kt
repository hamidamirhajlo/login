package com.example.userauthentication.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.userauthentication.JavaMailAPI
import com.example.userauthentication.R
import com.example.userauthentication.Utils.isValidEmail
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.edtPassword
import kotlinx.android.synthetic.main.fragment_login.toolbar
import kotlinx.android.synthetic.main.fragment_register.*

class LoginFragment : Fragment(R.layout.login_responsive) {

    private var client: GoogleSignInClient? = null
    private var isUsernameValid = false
    private var isPassValid = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setupToolbar()
//
//        //forget password
//        tvForgetPassword.setOnClickListener {
//            findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
//        }
//
//        btnGoRegister.setOnClickListener {
//            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
//        }
//
//        btnLogin.setOnClickListener {
//            //emailCheck(it)
//
//        }
//
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//
//        client = GoogleSignIn.getClient(requireContext(), gso)
//
//        googleSignIn.setOnClickListener {
//            val signInIntent = client?.signInIntent
//            startActivityForResult(signInIntent, 11)
//        }
//
//        edtUsername.addTextChangedListener {
//            isUsernameValid = !(it?.toString()?.length!! != 0 && !it.toString().isValidEmail())
//
//            btnLogin.isEnabled = isUsernameValid && isPassValid
//        }
//
//        edtPassword.addTextChangedListener {
//            isPassValid = it?.toString()?.length!! >= 6
//            btnLogin.isEnabled = isUsernameValid && isPassValid
//
//        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 11) {

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>?) {
        try {
            task?.getResult(ApiException::class.java)
            println(task?.result?.photoUrl)
        } catch (e: ApiException) {
            Toast.makeText(context, "api ex: $e", Toast.LENGTH_LONG).show()
        }
    }


    private fun emailCheck(it: View) {
        if (it?.toString()?.length!! != 0 && !it.toString().isValidEmail()) {
            inpEdtUsername.error = "Email address is not valid!"
            edtUsername.addTextChangedListener {

                if (it?.toString()?.length!! != 0 && !it.toString().isValidEmail()) {
                    inpEdtUsername.error = "Email address is not valid!"
                } else {
                    inpEdtUsername.isErrorEnabled = false
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