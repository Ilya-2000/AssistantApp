package com.impact.assistantapp.data.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Completable

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

    fun getCurrenUser(): FirebaseUser? {
        return auth.currentUser
    }

    fun authSignUp(email: String, password: String): Completable {
       return Completable.create {emitter ->
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "SignUp Success")
                        emitter.onComplete()
                    }
                }.addOnFailureListener {
                    emitter.onError(it)
                    Log.d(TAG, it.message.toString())
                }
        }
    }

///сделать в firebase коллекции firestore, запросы user делать в файле dataSource

}