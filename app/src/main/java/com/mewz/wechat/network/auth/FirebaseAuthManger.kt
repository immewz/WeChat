package com.mewz.wechat.network.auth

import com.google.firebase.auth.FirebaseAuth

object FirebaseAuthManger: AuthManger {

    private val mFirebaseAuth = FirebaseAuth.getInstance()

    override fun login(
        phoneNumber: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful && it.isComplete){
                onSuccess()
            }else{
                onFailure(it.exception?.message ?: "Check Internet Connection")
            }
        }
    }
}