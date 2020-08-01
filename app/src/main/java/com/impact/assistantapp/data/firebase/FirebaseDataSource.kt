package com.impact.assistantapp.data.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.impact.assistantapp.data.model.User
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable

class FirebaseDataSource {
    private var firestore = FirebaseFirestore.getInstance()
    private val TAG = "FirebaseDataSource"

    fun addUser(user: User): Completable {
        return Completable.create {emitter ->
            firestore.collection("users")
                .document(user.id)
                .set(user)
                .addOnSuccessListener {
                    Log.d(TAG, "AddUser: Success")
                    emitter.onComplete()
                }
                .addOnFailureListener {
                    emitter.onError(it)
                    Log.d(TAG, "AddUser: Fail")
                }

        }
    }

    fun getUser(id: String): Flowable<User> {
        return Flowable.create({ emitter ->
            firestore.collection("users")
                .document(id)
                .get()
                .addOnCompleteListener {task ->
                    emitter.onComplete()
                    if (task.isSuccessful) {
                        var result = task.result
                        var user = User(
                            result?.get("id").toString(),
                            result?.get("name").toString(),
                            result?.get("email").toString(),
                            result?.get("password").toString()
                        )
                        Log.d(TAG, "GetUser: Success" + task.result.toString())
                        emitter.onNext(user)
                    } else {
                        Log.d(TAG, "GetUser: Fail, not Success" + task.result.toString())
                    }
                }.addOnFailureListener {
                    Log.d(TAG, "GetUser: Fail" + it.message.toString())
                    emitter.onError(it)
                }
        }, BackpressureStrategy.ERROR)
    }
}