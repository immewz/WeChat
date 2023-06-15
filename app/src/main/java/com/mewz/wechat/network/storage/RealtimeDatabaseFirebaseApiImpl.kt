package com.mewz.wechat.network.storage


import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.mewz.wechat.data.vos.GroupVO
import com.mewz.wechat.data.vos.MessageVO
import com.mewz.wechat.data.vos.PrivateMessageVO

import java.io.ByteArrayOutputStream
import java.util.*

object RealtimeDatabaseFirebaseApiImpl: RealtimeFirebaseApi {

//    private val database: DatabaseReference = Firebase.database.reference

    private var database: DatabaseReference
    private var storageRef = FirebaseStorage.getInstance().reference

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

    override fun sendMessage(
        senderId: String,
        receiverId: String,
        timeStamp: Long,
        message: PrivateMessageVO
    ) {
        database.child("contactsAndMessages").child(senderId).child(receiverId)
            .child(timeStamp.toString()).setValue(message)
    }

    override fun getMessages(
        senderId: String,
        receiverId: String,
        onSuccess: (messageList: List<PrivateMessageVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("contactsAndMessages")
            .child(senderId)
            .child(receiverId)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    onFailure(error.message)
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val messageList = arrayListOf<PrivateMessageVO>()
                    snapshot.children.forEach { dataSnapShot ->
                        dataSnapShot.getValue(PrivateMessageVO::class.java)?.let {
                            messageList.add(it)
                        }
                    }
                    onSuccess(messageList)
                }
            })
    }

    override fun uploadAndSendImage(
        bitmap: Bitmap,
        onSuccess: (file: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val imageRef = storageRef.child("images/${UUID.randomUUID()}")
        val uploadTask = imageRef.putBytes(data)
        uploadTask.addOnFailureListener {
            Log.i("FileUpload", "File uploaded failed")
        }.addOnSuccessListener {
            Log.i("FileUpload", "File uploaded successful")
        }

        val urlTask = uploadTask.continueWithTask {
            return@continueWithTask imageRef.downloadUrl
        }.addOnCompleteListener {
            val imageUrl = it.result?.toString()
            if (imageUrl != null) {
                onSuccess(imageUrl)
            }
        }
    }
    override fun getChatHistoryUserId(
        senderId: String,
        onSuccess: (messageList: List<String>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("contactsAndMessages")
            .child(senderId)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    onFailure(error.message)
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val messageList = arrayListOf<String>()
                    snapshot.children.forEach { dataSnapShot ->
                        dataSnapShot.key?.let {
                            messageList.add(it)
                        }
                    }
                    onSuccess(messageList)
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

    override fun sendGroupMessage(groupId: Long,timeStamp:Long, message: PrivateMessageVO) {
        database.child("groups").child(groupId.toString()).child("messages")
            .child(timeStamp.toString()).setValue(message)
    }

    override fun getGroupMessages(
        groupId: Long,
        onSuccess: (messageList: List<PrivateMessageVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("groups")
            .child(groupId.toString())
            .child("messages")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    onFailure(error.message)
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val messageList = arrayListOf<PrivateMessageVO>()
                    snapshot.children.forEach { dataSnapShot ->
                        dataSnapShot.getValue(PrivateMessageVO::class.java)?.let {
                            messageList.add(it)
                        }
                    }
                    onSuccess(messageList)
                }
            })
    }

}