package com.mewz.wechat.network.storage

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.mewz.wechat.data.vos.MomentVO
import com.mewz.wechat.data.vos.MyMomentVO
import com.mewz.wechat.data.vos.UserVO
import java.io.ByteArrayOutputStream
import java.util.*

@SuppressLint("StaticFieldLeak")
object CloudFirestoreFirebaseApiImpl: CloudFirestoreFirebaseApi {

    private var database: FirebaseFirestore = Firebase.firestore
    private var storageRef = FirebaseStorage.getInstance().reference

    // General
    private var mMomentImages: String = ""
    override fun addUser(user: UserVO) {
        val userMap = hashMapOf(
            "id" to user.userId,
            "name" to user.userName,
            "phone" to user.phone,
            "email" to user.email,
            "password" to user.password,
            "date_of_birth" to user.dateOfBirth,
            "gender" to user.gender,
            "qr_code" to user.userId,
            "image_url" to user.imageUrl
        )

        database.collection("users")
            .document(user.userId)
            .set(userMap)
            .addOnSuccessListener { Log.d("FirebaseCall", "Successfully Added") }
            .addOnFailureListener { Log.i("FirebaseCall", "Failed Added") }
    }

    private fun changeBitmapToUrlString(bitmap: Bitmap): Task<Uri> {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val imageRef = storageRef.child("images/${UUID.randomUUID()}")
        val uploadTask = imageRef.putBytes(data)
        Log.i("ImageURL", uploadTask.toString())

        uploadTask.addOnFailureListener {
            Log.i("FileUpload", "File uploaded failed")
        }.addOnSuccessListener {
            Log.i("FileUpload", "File uploaded successful")
        }

        val urlTask = uploadTask.continueWithTask {
            return@continueWithTask imageRef.downloadUrl
        }
        return urlTask

    }

    override fun uploadAndUpdateProfileImage(bitmap: Bitmap, user: UserVO) {
        val urlTask = changeBitmapToUrlString(bitmap)

        urlTask.addOnCompleteListener {
            val imageUrl = it.result?.toString()
            Log.i("ImageURL", imageUrl.toString())
            addUser(
                UserVO(
                    userId = user.userId,
                    userName = user.userName,
                    phone = user.phone,
                    email = user.email,
                    password = user.password,
                    dateOfBirth = user.dateOfBirth,
                    gender = user.gender,
                    qrCode = user.userId,
                    imageUrl = imageUrl ?: ""
                )
            )
        }
    }


    override fun getUser(onSuccess: (users: List<UserVO>) -> Unit, onFailure: (String) -> Unit) {
        database.collection("users")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.localizedMessage ?: "Check Internet Connection")
                } ?: run {
                    val userList: MutableList<UserVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()

                    for (document in result){
                        val data = document.data
                        val id = data?.get("id") as String
                        val name = data["name"] as String
                        val phone = data["phone"] as String
                        val email = data["email"] as String
                        val password = data["password"] as String
                        val dateOfBirth = data["date_of_birth"] as String
                        val gender = data["gender"] as String
                        val qrCode = data["qr_code"] as String
                        val imageUrl = data["image_url"] as String
                        val user = UserVO(id, name, email, phone, password, dateOfBirth, gender, qrCode, imageUrl)
                        userList.add(user)
                    }
                    onSuccess(userList)
                }
            }
    }

    override fun createMoment(moment: MyMomentVO) {
        val momentMap = hashMapOf(
            "id" to moment.id,
            "user_id" to moment.userId,
            "user_name" to moment.userName,
            "user_profile_image" to moment.userProfileImage,
            "caption" to moment.caption,
            "image_url" to moment.imageUrl,
            "is_bookmarked" to moment.isBookmarked
        )

        database.collection("moments")
            .document(moment.id)
            .set(momentMap)
            .addOnSuccessListener {
                Log.i("FirebaseCall", "Successfully Created")
            }.addOnFailureListener {
                Log.i("FirebaseCall", "Failed Created")
            }
    }

    override fun uploadAndUpdateMomentImage(bitmap: Bitmap) {
        val urlTask = changeBitmapToUrlString(bitmap)

        urlTask.addOnCompleteListener {
            val imageUrl = it.result?.toString()
            mMomentImages += "$imageUrl,"
        }
    }

    override fun getMomentImages(): String {
        return mMomentImages
    }

    override fun getMoments(
        onSuccess: (moments: List<MyMomentVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.collection("moments")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.localizedMessage ?: "Check Internet Connection")
                } ?: run {
                    val momentList: MutableList<MyMomentVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val data = document.data
                        val id = data?.get("id") as String
                        val userId = data["user_id"] as? String ?: ""
                        val userName = data["user_name"] as String
                        val userProfileImage = data["user_profile_image"] as String
                        val caption = data["caption"] as String
                        val imageUrl = data["image_url"] as String
                        val isBookmarked = data["is_bookmarked"] as? Boolean ?: false
                        val moment = MyMomentVO(id,userId, userName, userProfileImage, caption, imageUrl,isBookmarked)
                        momentList.add(moment)
                    }
                    onSuccess(momentList)
                }
            }
    }


}