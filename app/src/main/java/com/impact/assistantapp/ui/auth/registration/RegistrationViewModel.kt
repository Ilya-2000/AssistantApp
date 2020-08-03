package com.impact.assistantapp.ui.auth.registration

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.impact.assistantapp.data.model.User
import com.impact.assistantapp.data.repositories.AuthRepository
import io.reactivex.CompletableObserver
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

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
        val user = User(
            _id.value.toString(),
            _name.value.toString(),
            _email.value.toString(),
            password.value.toString()
        )
        _user.value = user

    }

    fun setEmail(data: String) {
        _email.value = data
        Log.d(TAG, "setEmail, ${email.value}")
    }

    fun setPassword(data: String) {
        _password.value = data
        Log.d(TAG, "setPassword, ${password.value}")
    }

    fun setName(data: String) {
        _name.value = data
        Log.d(TAG, "setName, ${name.value}")
    }

    private fun setId(data: String) {
        _id.value = data
        Log.d(TAG, "setId: $id")
    }

    fun showMessage(message: String, context: Context) {
        Log.d(TAG, "Message: $message")
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun addUser() {
        val email = _email.value
        val password = _password.value
        val name = _name.value
        Log.d(TAG, "addUser: check email and password ${_email.value} ${_password.value}")
        if (email != null && password != null && email.isNotEmpty() && password.isNotEmpty() && name != null && name.isNotEmpty()) {
            if (email.contains("@") && password.length > 5) {
                authRepository.addAuthUser(email, password, name)
                    .observeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : CompletableObserver{
                        override fun onComplete() {
                            Log.d(TAG, "addUser: onComplete")
                        }

                        override fun onSubscribe(d: Disposable) {
                            Log.d(TAG, "addUser: onSubscribe $d")
                        }

                        override fun onError(e: Throwable) {
                            Log.d(TAG, "addUser: onError $e")
                        }

                    })



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