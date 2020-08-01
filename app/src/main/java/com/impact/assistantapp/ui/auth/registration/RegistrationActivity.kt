package com.impact.assistantapp.ui.auth.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.impact.assistantapp.R
import com.impact.assistantapp.databinding.ActivityLoginBinding
import com.impact.assistantapp.databinding.ActivityRegistrationBinding
import com.impact.assistantapp.ui.auth.login.LoginViewModel

class RegistrationActivity : AppCompatActivity() {
    private val TAG = "RegistrationActivity"
    private lateinit var registrationViewModel: RegistrationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding: ActivityRegistrationBinding = DataBindingUtil.setContentView(this, R.layout.activity_registration)
        dataBinding.lifecycleOwner = this
        registrationViewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)

        dataBinding.signUpButton.setOnClickListener {
            registrationViewModel.setEmail(dataBinding.emailRegistrationText.text.toString())
            registrationViewModel.setName(dataBinding.nameRegistrationText.text.toString())
            val isEqual = registrationViewModel.checkPassword(dataBinding.passwordRegistrationText.text.toString(),
                dataBinding.password2RegistrationText.text.toString())
            if (isEqual) {
                registrationViewModel.setPassword(dataBinding.passwordRegistrationText.text.toString())
                registrationViewModel.setUser()
                registrationViewModel.addUser()
            } else {
                Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_LONG).show()
            }


        }
    }
}