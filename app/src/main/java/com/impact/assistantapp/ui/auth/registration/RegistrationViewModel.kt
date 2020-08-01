package com.impact.assistantapp.ui.auth.registration

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.impact.assistantapp.data.model.User
import com.impact.assistantapp.data.repositories.AuthRepository

class RegistrationViewModel : ViewModel() {
    private val TAG = "RegistrationViewModel"
    private val authRepository = AuthRepository()

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private val _id = MutableLiveData<String>()
    val id: LiveData<String>
        get() = _id

    private val _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    init {
        _user.value = null
        _email.value = ""
        _id.value = ""
        _password.value = ""
        _name.value = ""
    }

    fun setUser() {
        setId()
        val user = User(
            _id.value.toString(),
            _name.value.toString(),
            _email.value.toString(),
            password.value.toString()
        )
        _user.postValue(user)

    }

    fun setEmail(data: String) {
        _email.postValue(data)
        Log.d(TAG, "setEmail, $data")
    }

    fun setPassword(data: String) {
        _password.postValue(data)
        Log.d(TAG, "setPassword, $data")
    }

    fun setName(data: String) {
        _name.postValue(data)
    }

    private fun setId() {
        _id.postValue(authRepository.getCurrentUser()?.uid.toString())
    }

    fun showMessage(message: String, context: Context) {
        Log.d(TAG, "Message: $message")
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun addUser() {
        var email = _email.value
        var password = _password.value
        if (email != null && password != null) {
            if (email.contains("@") && password.length > 5) {
                authRepository.addAuthUser(email, password)

                writeNewUser()
            } else {
                Log.d(TAG, "addUser: email or password does not meet the requirements of the condition $email $password")
            }
        } else {
            Log.d(TAG, "addUser: email or password empty $email $password")
        }
    }

    private fun writeNewUser() {
        val user = _user.value
        if (user != null) {
            authRepository.addUser(user)
            Log.d(TAG, "writeNewUser: Write new")
        } else {
            Log.d(TAG, "writeNewUser: user is empty")
        }
    }

    fun checkPassword(password1: String, password2: String): Boolean {
        return password1 == password2
    }


}