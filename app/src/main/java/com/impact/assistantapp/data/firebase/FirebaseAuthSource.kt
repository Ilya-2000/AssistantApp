package com.impact.assistantapp.data.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.impact.assistantapp.data.model.User
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FirebaseAuthSource {
    private var auth  = FirebaseAuth.getInstance()
    private var firestore = FirebaseFirestore.getInstance()
    private val TAG = "FirebaseAuthSource"

    fun signIn(email: String, password: String): Completable {
       return  Completable.create {emitter ->
           auth.signInWithEmailAndPassword(email, password)
               .addOnCompleteListener {task ->
                   if (task.isSuccessful) {
                       emitter.onComplete()
                       Log.d(TAG, "SignIn Success")
                   } else {
                       Log.d(TAG, "SignIn Error")
                   }
               }.addOnFailureListener {
                   emitter.onError(it)
                   Log.d(TAG, it.message.toString())
               }

       }

    }

    fun getCurrentUser(): FirebaseUser? {
        Log.d(TAG, "Current user ${auth.currentUser.toString()}")
        return auth.currentUser
    }

    fun authSignUp(email: String, password: String, name: String): Completable{
       return Completable.create {emitter ->
            auth.createUserWithEmailAndPassword(email, password)
                .addOnFailureListener {
                    emitter.onError(it)
                    Log.d(TAG, it.message.toString())
                }.addOnCompleteListener {
                    val id = it.result?.user?.uid
                    Log.d(TAG,"authSignUp:uid is ${it.result?.user?.uid}" )
                    if (!id.isNullOrEmpty()) {
                        val user = User(
                            id,
                            name,
                            email,
                            password
                        )

                        val map = hashMapOf<String, String>(
                            "id" to user.id,
                            "name" to user.name,
                            "email" to user.email,
                            "password" to user.password
                        )
                        addUser(map, id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(object : CompletableObserver{
                                override fun onComplete() {
                                    Log.d(TAG,"authSignUp/addUser: onComplete" )
                                }

                                override fun onSubscribe(d: Disposable) {
                                    Log.d(TAG,"authSignUp/addUser: onSubscribe $d" )
                                }

                                override fun onError(e: Throwable) {
                                    Log.d(TAG,"authSignUp/addUser: onError $e" )
                                }
                            })
                    } else {
                        Log.d(TAG,"authSignUp: id is empty or null" )
                    }

                    emitter.onComplete()
                }
        }
    }

    private fun addUser(map: HashMap<String, String>, id: String): Completable {
        return Completable.create {emitter ->
            firestore.collection("users")
                .document(id)
                .set(map)
                .addOnFailureListener {
                    emitter.onError(it)
                    Log.d(TAG, "AddUser: Fail")
                }.addOnSuccessListener {
                    Log.d(TAG, "AddUser: Success")
                    emitter.onComplete()
                }


        }
    }



}