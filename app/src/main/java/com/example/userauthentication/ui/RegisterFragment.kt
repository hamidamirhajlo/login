package com.example.userauthentication.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.userauthentication.R
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class RegisterFragment : Fragment(R.layout.fragment_register) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()

        btnGoLogin.setOnClickListener {
            findNavController().navigateUp()
        }

        val cores = Runtime.getRuntime().availableProcessors()
        val executor = ThreadPoolExecutor(
            cores * 2,
            cores * 2,
            60L,
            TimeUnit.SECONDS,
            LinkedBlockingQueue<Runnable>()
        )

/*        val task = FirebaseAuth.getInstance().signInAnonymously()

        task.addOnCompleteListener(executor) { newTask ->
            println("on complete: $newTask")
            if (newTask.isSuccessful) {
                //on success
            } else {
                //on file
            }
        }*/


    }

    private fun setupToolbar() {
        toolbar.setupWithNavController(
            findNavController(), AppBarConfiguration(findNavController().graph)
        )
    }
}