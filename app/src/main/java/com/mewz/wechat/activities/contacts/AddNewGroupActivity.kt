package com.mewz.wechat.activities.contacts

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.mewz.wechat.activities.BaseActivity
import com.mewz.wechat.activities.chats.ChatDetailActivity
import com.mewz.wechat.adapters.MemberAdapter
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.databinding.ActivityAddNewGroupBinding
import com.mewz.wechat.mvp.presenters.NewGroupPresenter
import com.mewz.wechat.mvp.presenters.impls.NewGroupPresenterImpl
import com.mewz.wechat.mvp.views.NewGroupView
import com.mewz.wechat.views.viewpods.ContactViewPod

class AddNewGroupActivity : BaseActivity(), NewGroupView {

    private lateinit var binding: ActivityAddNewGroupBinding

    private lateinit var mAdapter: MemberAdapter

    private lateinit var mViewPod: ContactViewPod

    private lateinit var mPresenter: NewGroupPresenter

    private var mMemberList: ArrayList<UserVO> = arrayListOf()
    private var mUserList: List<UserVO> = listOf()

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, AddNewGroupActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewGroupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpPresenter()
        setUpRecyclerView()
        setUpViewPod()
        setUpListeners()
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<NewGroupPresenterImpl, NewGroupView>()
    }

    private fun setUpListeners() {
        binding.btnDismiss.setOnClickListener {
            onBackPressed()
        }

        binding.btnCreateGroup.setOnClickListener {
            val mUserIdList = arrayListOf<String>()
            val groupName = binding.etGroupName.text.toString()
            for(member in mMemberList) {
                mUserIdList.add(member.userId)
            }
            mUserIdList.add(mPresenter.getUserId())
            if(groupName.isNotEmpty()) {
                mPresenter.onTapCreateButton(System.currentTimeMillis(),groupName,mUserIdList.toList())
                finish()
            }
        }
    }

    private fun setUpViewPod() {
        mViewPod = binding.vpContactGroup.root
        mViewPod.setUpContactGroupViewPod(mPresenter, mPresenter)

        mPresenter.getContacts(mPresenter.getUserId())
    }

    private fun setUpRecyclerView() {
        mAdapter = MemberAdapter()
        binding.rvMemberList.adapter = mAdapter
        binding.rvMemberList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun navigateToChatDetailScreen(userId: String) {
        startActivity(ChatDetailActivity.newIntent(this, userId, ""))
    }

    private fun getAlphabetList(nameList:List<String>) : List<Char> {
        val nameMapList = nameList.groupBy { it[0] }
        val alphabetList = arrayListOf<Char>()
        for(key in nameMapList.keys) {
            alphabetList.add(key)
        }
        return alphabetList.sorted()
    }

    override fun showContacts(userList: List<UserVO>) {
        mUserList = userList
        val nameList = arrayListOf<String>()
        for(contact in userList) {
            nameList.add(0,contact.userName)
        }
        mViewPod.setNewData(getAlphabetList(nameList),userList,true)
    }

    override fun addUserToGroup(userId: String, isChecked: Boolean) {
        if(isChecked) {
            for(user in mUserList) {
                if(userId == user.userId) {
                    mMemberList.add(user)
                    Log.d("NewGroup","$mMemberList")
                }
            }
        } else {
            for(user in mUserList) {
                if(userId == user.userId) {
                    mMemberList.remove(user)
                }
            }
        }

        mAdapter.setNewData(mMemberList)
    }
}