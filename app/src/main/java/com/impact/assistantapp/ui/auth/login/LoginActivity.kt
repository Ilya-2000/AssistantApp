package com.impact.assistantapp.ui.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.impact.assistantapp.R
import com.impact.assistantapp.databinding.ActivityLoginBinding
import com.impact.assistantapp.ui.auth.registration.RegistrationActivity

class LoginActivity : AppCompatActivity() {
    private val TAG = "LoginActivity"
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        val dataBinding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        dataBinding.lifecycleOwner = this
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


        dataBinding.registrationLoginButton.setOnClickListener {
            Toast.makeText(this, "Reg click", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        dataBinding.signInLoginButton.setOnClickListener {
            loginViewModel.getCurrentUser()
            loginViewModel.setEmail(dataBinding.emailLoginText.text.toString())
            loginViewModel.setPassword(dataBinding.passLoginText.text.toString())
            loginViewModel.email.observe(this, Observer {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                Log.d(TAG, it)
            })
            loginViewModel.password.observe(this, Observer {
                Log.d(TAG, it)
            })

            loginViewModel.id.observe(this, Observer {
                Log.d(TAG, it)
            })
            //*loginViewModel.email.value = dataBinding.emailLoginText.text.toString()

        }


    }
}