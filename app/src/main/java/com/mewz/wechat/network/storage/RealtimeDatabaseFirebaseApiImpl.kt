package com.mewz.wechat.network.storage


import android.util.Log
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mewz.wechat.data.vos.GroupVO

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

    override fun addGroup(timeStamp: Long, groupName: String, userList: List<String>) {
        database.child("groups").child(timeStamp.toString()).child("name").setValue(groupName)
        database.child("groups").child(timeStamp.toString()).child("userIdList").setValue(userList)
        database.child("groups").child(timeStamp.toString()).child("id").setValue(timeStamp)
    }

    override fun getGroups(
        onSuccess: (groupIdList: List<GroupVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("groups")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    onFailure(error.message)
                }
                override fun onDataChange(snapshot: DataSnapshot) {
                    val groupList = arrayListOf<GroupVO>()
                    snapshot.children.forEach { dataSnapShot ->
                        dataSnapShot.getValue(GroupVO::class.java)?.let {
                            groupList.add(it)
                        }
                    }
                    onSuccess(groupList)
                }
            })
    }
}