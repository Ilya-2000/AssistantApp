package com.impact.assistantapp.ui.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.impact.assistantapp.data.model.User
import com.impact.assistantapp.data.repositories.AuthRepository
import io.reactivex.CompletableObserver
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel: ViewModel() {
    private val TAG = "LoginViewModel"
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

    fun signIn() {
        var email = _email.value
        var password = _password.value
        if (email != null && password != null) {
            if (email.contains("@") && password.length > 5) {
                authRepository.signIn(email, password)
                    .observeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : CompletableObserver {
                        override fun onComplete() {
                            Log.d(TAG, "signIn: onComplete")
                            var firebaseUser = authRepository.getCurrentUser()
                            _id.value = firebaseUser?.uid
                            var id = _id.value
                            if (id != null) {
                                authRepository.getUser(id)
                                    .observeOn(Schedulers.io())
                                    .subscribeOn(AndroidSchedulers.mainThread())
                                    .toObservable()
                                    .subscribe(object : Observer<User> {
                                        override fun onComplete() {
                                            Log.d(TAG, "signIn/getUser: onComplete")
                                        }

                                        override fun onSubscribe(d: Disposable) {
                                            Log.d(TAG, "signIn/getUser: onSubscribe $d")
                                        }

                                        override fun onNext(t: User) {
                                            Log.d(TAG, "signIn/getUser: onNext")
                                            _user.postValue(t)
                                        }

                                        override fun onError(e: Throwable) {
                                            Log.d(TAG, "signIn/getUser: onError ${e.message}")
                                        }

                                    })


                            } else {
                                Log.d(TAG, "signIn: id is empty")
                            }
                        }

                        override fun onSubscribe(d: Disposable) {
                            Log.d(TAG, "signIn: onSubscribe $d")
                        }

                        override fun onError(e: Throwable) {
                            Log.d(TAG, "signIn: onError ${e.message}")
                        }

                    })
            } else {
                Log.d(TAG, "signIn: email or password does not meet the requirements of the condition $email $password")
            }
        } else {
            Log.d(TAG, "signIn: email or password is empty $email $password")
        }

    }

    fun getCurrentUser() {
        var a = authRepository.getCurrentUser().toString()
        _id.value = a
        Log.d(TAG, "getCurrentUser: $a")
    }

    fun setUser(data: User) {
        _user.postValue(data)

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



    fun showMessage(message: String, context: Context) {
        Log.d(TAG, "Message: $message")
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}