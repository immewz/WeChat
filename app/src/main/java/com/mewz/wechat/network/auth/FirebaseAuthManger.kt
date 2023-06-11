package com.mewz.wechat.network.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.mewz.wechat.data.vos.UserVO

object FirebaseAuthManger: AuthManger {

    private val mFirebaseAuth = FirebaseAuth.getInstance()

    override fun login(
        phone: String,
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

    override fun register(
        userName: String,
        email: String,
        phone: String,
        password: String,
        dateOfBirth: String,
        gender: String,
        imageUrl: String,
        onSuccess: (user: UserVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful && it.isComplete){
                mFirebaseAuth.currentUser?.updateProfile(
                    UserProfileChangeRequest.Builder().setDisplayName(userName).build()
                )
                onSuccess(UserVO(getUserId(), userName, email, phone, password, dateOfBirth, gender, imageUrl))
            }else{
                onFailure(it.exception?.message ?: "Please check internet connection")
            }
        }
    }

    override fun getUserId(): String {
        return mFirebaseAuth.currentUser?.uid.toString()
    }

}