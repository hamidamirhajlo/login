package com.example.userauthentication.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.userauthentication.R
import kotlinx.android.synthetic.main.fragment_code_validation.*

class CodeValidationFragment : Fragment(R.layout.fragment_code_validation) {

    private var code = ""
    private var timeLeft = 120000L;
    private lateinit var countDownTimer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey("code")) {
                code = it.getString("code").toString()
                Toast.makeText(context, code, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        btnValidateResetCode.isEnabled = false

        countDownTimer = object : CountDownTimer(timeLeft, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                updateTimer()
            }

            override fun onFinish() {
                findNavController().navigateUp()

            }

        }.start()

        edtCodeValidation.addTextChangedListener {
            if (it.toString() == code) {
                findNavController().navigate(R.id.action_codeValidationFragment_to_changePasswordFragment)
            }
        }

        btnValidateResetCode.setOnClickListener {
            if (edtCodeValidation.text.toString() == code) {
            }
        }
    }

    private fun updateTimer() {
        val min = (timeLeft / 60000).toInt()
        val sec = (timeLeft % 60000 / 1000).toInt()

        var timerLeftText = "0$min"
        timerLeftText += ":"
        if (sec < 10) timerLeftText += "0"
        timerLeftText += sec

        tvCounterValidation.text = timerLeftText
    }

    private fun setupToolbar() {
        toolbar.setupWithNavController(
            findNavController(), AppBarConfiguration(findNavController().graph)
        )
    }
}