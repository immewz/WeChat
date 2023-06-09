package com.mewz.wechat.activities.moments

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mewz.wechat.R
import com.mewz.wechat.activities.BaseActivity
import com.mewz.wechat.activities.login.LoginActivity
import com.mewz.wechat.activities.login.RegisterActivity
import com.mewz.wechat.adapters.NewMomentImageAdapter
import com.mewz.wechat.databinding.ActivityNewMomentBinding
import com.mewz.wechat.mvp.presenters.NewMomentPresenter
import com.mewz.wechat.mvp.presenters.impls.NewMomentPresenterImpl
import com.mewz.wechat.mvp.views.NewMomentView

class NewMomentActivity : BaseActivity(), NewMomentView {

    private lateinit var binding: ActivityNewMomentBinding

    private lateinit var mAdapter: NewMomentImageAdapter

    private lateinit var mPresenter: NewMomentPresenter

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
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<NewMomentPresenterImpl, NewMomentView>()
    }

    private fun setUpListeners() {
        binding.btnDismiss.setOnClickListener {
            mPresenter.onTapBackButton()
        }

        binding.btnCreateMoment.setOnClickListener {
            mPresenter.onTapCreateButton()
        }
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

    override fun createNewMoment() {
        Toast.makeText(this,"Create", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Selected Picture"),
            RegisterActivity.PICK_IMAGE_REQUEST
        )
    }
}