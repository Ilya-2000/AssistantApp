package com.impact.assistantapp.data.repositories

import com.google.firebase.auth.FirebaseUser
import com.impact.assistantapp.data.firebase.FirebaseAuthSource
import com.impact.assistantapp.data.firebase.FirebaseDataSource
import com.impact.assistantapp.data.model.User
import io.reactivex.Completable
import io.reactivex.Flowable

class AuthRepository {
    private val firebaseDataSource = FirebaseDataSource()
    private val firebaseAuthSource = FirebaseAuthSource()

    fun addAuthUser(email: String, password: String): Completable {
        return firebaseAuthSource.authSignUp(email, password)
    }

    fun signIn(email: String, password: String): Completable {
        return firebaseAuthSource.signIn(email, password)
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuthSource.getCurrenUser()
    }

    fun addUser(user: User): Completable {
        return firebaseDataSource.addUser(user)
    }

    fun getUser(id: String): Flowable<User> {
        return firebaseDataSource.getUser(id)
    }
}