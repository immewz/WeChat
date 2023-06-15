package com.mewz.wechat.activities.chats

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mewz.wechat.activities.BaseActivity
import com.mewz.wechat.activities.MainActivity
import com.mewz.wechat.adapters.ChatDetailImageAdapter
import com.mewz.wechat.adapters.MessageAdapter
import com.mewz.wechat.data.vos.GroupVO
import com.mewz.wechat.data.vos.MessageVO
import com.mewz.wechat.data.vos.PrivateMessageVO
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.databinding.ActivityChatDetailBinding
import com.mewz.wechat.mvp.presenters.ChatDetailPresenter
import com.mewz.wechat.mvp.presenters.impls.ChatDetailPresenterImpl
import com.mewz.wechat.mvp.views.ChatDetailView
import java.io.IOException

class ChatDetailActivity : BaseActivity(), ChatDetailView {

    private lateinit var binding: ActivityChatDetailBinding

    private lateinit var mMessageAdapter: MessageAdapter
    private lateinit var mImageAdapter: ChatDetailImageAdapter

    private lateinit var mPresenter: ChatDetailPresenter

    private var mUserName: String = ""
    private var mUserProfileImage: String = ""
    private var mReceiverId = ""
    private var mGroupId = ""
    private var mGroupName = ""

    private var REQUEST_CODE_GALLERY = 1111
    private var REQUEST_IMAGE_CAPTURE = 2222
    private var mImageList: ArrayList<String> = arrayListOf()

    companion object{
        private const val EXTRA_USER_ID = "EXTRA_USER_ID"
        private const val EXTRA_GROUP_ID = "EXTRA_GROUP_ID"
        fun newIntent(context: Context, userId: String, groupId: String): Intent {
            val intent = Intent(context, ChatDetailActivity::class.java)
            intent.putExtra(EXTRA_USER_ID, userId)
            intent.putExtra(EXTRA_GROUP_ID, groupId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpPresenter()

        setUpRecyclerView()

        mReceiverId = intent.getStringExtra(EXTRA_USER_ID) ?: ""
        mGroupId = intent.getStringExtra(EXTRA_GROUP_ID) ?: ""

        setUpListeners()

        mPresenter.onUiReady(this, this)

        if (mGroupId.isEmpty()) {
            mPresenter.getMessages(mPresenter.getUserId(), mReceiverId)
        } else {
            mPresenter.getGroupMessages(mGroupId.toLong())
        }

    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<ChatDetailPresenterImpl, ChatDetailView>()
    }

    private fun setUpRecyclerView() {
        mMessageAdapter = MessageAdapter()
        binding.rvMessage.adapter = mMessageAdapter
        binding.rvMessage.layoutManager =
            LinearLayoutManager(this@ChatDetailActivity)

        mImageAdapter = ChatDetailImageAdapter()
        binding.rvImages.adapter = mImageAdapter
        binding.rvMessage.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
    }

    private fun setUpListeners() {
        binding.btnChatDetailBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnSendMessage.setOnClickListener {
            val message = binding.etMessage.text.toString()

            if (message.isNotEmpty() || mImageList.isNotEmpty()) {

                if (mGroupId.isEmpty()) {
                    sendPrivateChatMessage(message = message)
                } else {
                    sendGroupChatMessage(message = message)
                }
            }
            mImageList.clear()
            mImageAdapter.setNewData(mImageList)
            binding.etMessage.text?.clear()
        }

        binding.btnPhoto.setOnClickListener {
            mPresenter.onTapGetImageButton()
        }
    }

    private fun sendPrivateChatMessage(message: String) {
        Log.i("ImageCount",mImageList.size.toString())
        if (mImageAdapter.itemCount > 0 && message.isEmpty()) {
            for (image in mImageList) {
                val timeStamp = System.currentTimeMillis()
                mPresenter.sendMessage(mPresenter.getUserId(), mReceiverId, timeStamp, PrivateMessageVO(userId = mPresenter.getUserId(), userName = mUserName, userProfileImage = mUserProfileImage, timeStamp = timeStamp, file = image, message = "", groupName = ""))
                mPresenter.sendMessage(mReceiverId, mPresenter.getUserId(), timeStamp, PrivateMessageVO(userId = mPresenter.getUserId(), userName = mUserName, userProfileImage = mUserProfileImage, timeStamp = timeStamp, file = image, message = "", groupName = ""))
            }
        } else if (message.isNotEmpty() && mImageList.isEmpty()) {
            val timeStamp = System.currentTimeMillis()
            mPresenter.sendMessage(mPresenter.getUserId(), mReceiverId, timeStamp, PrivateMessageVO(userId = mPresenter.getUserId(), userName = mUserName, userProfileImage = mUserProfileImage, timeStamp = timeStamp, file = "", message = message, groupName = ""))
            mPresenter.sendMessage(mReceiverId, mPresenter.getUserId(), timeStamp, PrivateMessageVO(userId = mPresenter.getUserId(), userName = mUserName, userProfileImage = mUserProfileImage, timeStamp = timeStamp, file = "", message = message, groupName = ""))
        } else {
            val newTimeStamp = System.currentTimeMillis()
            mPresenter.sendMessage(mPresenter.getUserId(), mReceiverId, newTimeStamp, PrivateMessageVO(userId = mPresenter.getUserId(), userName = mUserName, userProfileImage = mUserProfileImage, timeStamp = newTimeStamp, file = "", message = message, groupName = ""))
            mPresenter.sendMessage(mReceiverId, mPresenter.getUserId(), newTimeStamp, PrivateMessageVO(userId = mPresenter.getUserId(), userName = mUserName, userProfileImage = mUserProfileImage, timeStamp = newTimeStamp, file = "", message = message, groupName = ""))

            for (image in mImageList) {
                val timeStamp = System.currentTimeMillis()
                mPresenter.sendMessage(mPresenter.getUserId(), mReceiverId, timeStamp, PrivateMessageVO(userId = mPresenter.getUserId(), userName = mUserName, userProfileImage = mUserProfileImage, timeStamp = timeStamp, file = image, message = "", groupName = ""))
                mPresenter.sendMessage(mReceiverId, mPresenter.getUserId(), timeStamp, PrivateMessageVO(userId = mPresenter.getUserId(), userName = mUserName, userProfileImage = mUserProfileImage, timeStamp = timeStamp, file = image, message = "", groupName = ""))
            }
        }
    }

    private fun sendGroupChatMessage(message: String) {
        if (mImageAdapter.itemCount > 0 && message.isEmpty()) {
            for (image in mImageList) {
                val timeStamp = System.currentTimeMillis()
                mPresenter.sendGroupMessage(mGroupId.toLong(),timeStamp, PrivateMessageVO(userId = mPresenter.getUserId(), userName = mUserName, userProfileImage = mUserProfileImage, timeStamp = timeStamp, file = image, message = "", groupName = mGroupName))
            }
        } else if (message.isNotEmpty() && mImageList.isEmpty())  {
            val timeStamp = System.currentTimeMillis()
            mPresenter.sendGroupMessage(mGroupId.toLong(),timeStamp, PrivateMessageVO(userId = mPresenter.getUserId(), userName = mUserName, userProfileImage = mUserProfileImage, timeStamp = timeStamp, file = "", message = message, groupName = mGroupName))
        } else {
            val newTimeStamp = System.currentTimeMillis()
            mPresenter.sendGroupMessage(mGroupId.toLong(),newTimeStamp, PrivateMessageVO(userId = mPresenter.getUserId(), userName = mUserName, userProfileImage = mUserProfileImage, timeStamp = newTimeStamp, file = "", message = message, groupName = mGroupName))

            for (image in mImageList) {
                val timeStamp = System.currentTimeMillis()
                mPresenter.sendGroupMessage(mGroupId.toLong(),timeStamp,
                    PrivateMessageVO(userId = mPresenter.getUserId(), userName = mUserName, userProfileImage = mUserProfileImage, timeStamp = timeStamp, file = image, message = "", groupName = mGroupName))
            }
        }
    }

    override fun showMessages(messageList: List<PrivateMessageVO>) {
        mMessageAdapter.setNewData(mPresenter.getUserId(), messageList)
        binding.rvMessage.scrollToPosition(messageList.size - 1)
    }

    override fun showGroupMessages(messageList: List<PrivateMessageVO>) {
        mMessageAdapter.setNewData(mPresenter.getUserId(), messageList)
        binding.rvMessage.scrollToPosition(messageList.size - 1)
    }

    override fun showUsers(userList: List<UserVO>) {
        for (user in userList) {
            if (mReceiverId == user.userId) {
                binding.tvChatDetailName.text = user.userName

                Glide.with(this)
                    .load(user.imageUrl)
                    .into(binding.ivChatDetailProfileImage)
            }

            if (mPresenter.getUserId() == user.userId) {
                mUserName = user.userName
                mUserProfileImage = user.imageUrl
            }
        }
    }

    override fun getGroups(groupList: List<GroupVO>) {
        for (group in groupList) {
            if (mGroupId == group.id.toString()) {
                binding.tvChatDetailName.text = group.name
                mGroupName = group.name
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ((requestCode == REQUEST_CODE_GALLERY || requestCode == REQUEST_IMAGE_CAPTURE) && resultCode == Activity.RESULT_OK) {

            val filePath = data?.data
            if(requestCode == REQUEST_IMAGE_CAPTURE) {
                Toast.makeText(this, "You take a photo", Toast.LENGTH_SHORT).show()
                val imageBitmap = data?.extras?.get("data") as Bitmap
                mPresenter.uploadAndSendImage(imageBitmap)
                return
            }

            Toast.makeText(this, "You choose a photo", Toast.LENGTH_SHORT).show()

            try {
                filePath?.let { fileUrl ->
                    if (Build.VERSION.SDK_INT >= 29) {
                        val source = ImageDecoder.createSource(this.contentResolver, fileUrl)
                        val bitmapImage = ImageDecoder.decodeBitmap(source)
                        mPresenter.uploadAndSendImage(bitmapImage)
                    } else {
                        val bitmapImage = MediaStore.Images.Media.getBitmap(
                            applicationContext.contentResolver, fileUrl
                        )
                        mPresenter.uploadAndSendImage(bitmapImage)
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun showGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Select Upload Image"),
            REQUEST_CODE_GALLERY
        )
    }

    override fun getImageUrlForFile(file: String) {
        mImageList.add(file)
        mImageAdapter.setNewData(mImageList.toList())
    }


    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }



}