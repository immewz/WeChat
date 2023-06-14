package com.mewz.wechat.activities.moments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mewz.wechat.activities.BaseActivity
import com.mewz.wechat.activities.login.RegisterActivity
import com.mewz.wechat.adapters.NewMomentImageAdapter
import com.mewz.wechat.data.vos.MyMomentVO
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.databinding.ActivityNewMomentBinding
import com.mewz.wechat.mvp.presenters.NewMomentPresenter
import com.mewz.wechat.mvp.presenters.impls.NewMomentPresenterImpl
import com.mewz.wechat.mvp.views.NewMomentView
import java.io.IOException

class NewMomentActivity : BaseActivity(), NewMomentView {

    private lateinit var binding: ActivityNewMomentBinding

    private lateinit var mAdapter: NewMomentImageAdapter

    private lateinit var mPresenter: NewMomentPresenter

//    private var bitmap: Bitmap? = null
//    private var mMoment: MyMomentVO? = null
//    private var momentImages: String = ""
    private var userName: String = ""
    private var userProfileImage: String = ""
    private var userId:String = ""

    companion object{
        const val PICK_IMAGE_REQUEST = 1111

        fun newIntent(context: Context): Intent {
            return Intent(context, NewMomentActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewMomentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpPresenter()

        setUpRecyclerView()
        setUpListeners()

        mPresenter.onUiReady(this, this)
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<NewMomentPresenterImpl, NewMomentView>()
    }

    private fun setUpListeners() {
        binding.btnDismiss.setOnClickListener {
            mPresenter.onTapBackButton()
        }

        binding.btnCreateMoment.setOnClickListener {
            mPresenter.onTapCreateButton(getMomentPost())
            finish()
        }
    }

    private fun getMomentPost(): MyMomentVO {
        val caption = binding.etCaptionNewMoment.text.toString()
        return MyMomentVO(
            System.currentTimeMillis().toString(),
            userId,
            userName,
            userProfileImage,
            caption,
            mPresenter.getMomentImages().dropLast(1)
        )
    }

    private fun setUpRecyclerView() {
        mAdapter = NewMomentImageAdapter(mPresenter)
        binding.rvNewMomentImage.adapter = mAdapter
        binding.rvNewMomentImage.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun navigateToBackScreen() {
        onBackPressed()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }

            val filePath = data.data
            mAdapter.setNewData(filePath.toString())

            try {
                filePath?.let { fileUrl ->
                    if (Build.VERSION.SDK_INT >= 29) {
                        val source = ImageDecoder.createSource(this.contentResolver, fileUrl)
                        val bitmapImage = ImageDecoder.decodeBitmap(source)
                        mPresenter.createMomentImages(bitmapImage)
                    } else {
                        val bitmapImage = MediaStore.Images.Media.getBitmap(
                            applicationContext.contentResolver, fileUrl
                        )
                        mPresenter.createMomentImages(bitmapImage)
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Selected Picture"),
            RegisterActivity.PICK_IMAGE_REQUEST
        )
    }

    override fun showUserInformation(userList: List<UserVO>) {
        for (user in userList) {
            if (mPresenter.getUserId() == user.userId) {

                userId = user.userId
                userName = user.userName
                userProfileImage = user.imageUrl

                binding.tvProfileName.text = user.userName

                Glide.with(this)
                    .load(user.imageUrl)
                    .into(binding.ivProfileImage)
            }
        }
    }
}