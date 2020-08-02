package com.impact.assistantapp.data.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable

class FirebaseAuthSource {
    private var auth  = FirebaseAuth.getInstance()
    private val TAG = "FirebaseAuth"

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

    fun authSignUp(email: String, password: String): Flowable<FirebaseUser> {
       return Flowable.create ({emitter ->
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "SignUp Success")
                        emitter.onComplete()
                    }
                }.addOnSuccessListener {
                    val a = it.user
                    if (a != null) {
                        emitter.onNext(a)
                    }
                }.addOnFailureListener {
                    emitter.onError(it)
                    Log.d(TAG, it.message.toString())
                }
        },BackpressureStrategy.ERROR)
    }

///сделать в firebase коллекции firestore, запросы user делать в файле dataSource

}