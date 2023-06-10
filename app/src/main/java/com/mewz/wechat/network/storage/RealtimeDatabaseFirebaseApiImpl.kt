package com.mewz.wechat.network.storage


import android.util.Log
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object RealtimeDatabaseFirebaseApiImpl: RealtimeFirebaseApi {

//    private val database: DatabaseReference = Firebase.database.reference

    private var database: DatabaseReference

    init {
        val databaseUrl =
            "https://wechat-app-c3c78-default-rtdb.asia-southeast1.firebasedatabase.app"
        database = FirebaseDatabase.getInstance(databaseUrl).reference
    }

    override fun getOtp(onSuccess: (code: String) -> Unit, onFailure: (String) -> Unit) {
        database.child("code").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                onSuccess(snapshot.value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                onFailure(error.message)
            }

        })
    }
}