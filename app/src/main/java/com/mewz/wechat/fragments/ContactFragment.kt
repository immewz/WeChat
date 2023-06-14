package com.mewz.wechat.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mewz.wechat.R
import com.mewz.wechat.activities.chats.ChatDetailActivity
import com.mewz.wechat.activities.contacts.AddNewContactActivity
import com.mewz.wechat.activities.contacts.AddNewGroupActivity
import com.mewz.wechat.adapters.AlphabetAdapter
import com.mewz.wechat.adapters.ContactAdapter
import com.mewz.wechat.adapters.ContactGroupAdapter
import com.mewz.wechat.adapters.GroupAdapter
import com.mewz.wechat.data.vos.GroupVO
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.databinding.FragmentContactBinding
import com.mewz.wechat.delegtes.ChatItemViewHolderDelegate
import com.mewz.wechat.delegtes.GroupItemViewHolderDelegate
import com.mewz.wechat.mvp.presenters.ContactPresenter
import com.mewz.wechat.mvp.presenters.impls.ContactPresenterImpl
import com.mewz.wechat.mvp.views.ContactView
import com.mewz.wechat.utils.DummyData
import com.mewz.wechat.utils.DummyData.getAlphabetList
import com.mewz.wechat.views.viewpods.ContactViewPod

class ContactFragment : Fragment(), ContactView {

    private lateinit var binding: FragmentContactBinding

    private lateinit var mAdapter: GroupAdapter

    private lateinit var mViewPod: ContactViewPod

    private lateinit var mPresenter: ContactPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_contact, container, false)

        binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()

        setUpRecyclerView()
        setUpViewPod()
        setUpListeners()
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[ContactPresenterImpl::class.java]
        mPresenter.initPresenter(this)
    }

    private fun setUpListeners() {
        binding.btnAddFriend.setOnClickListener {
            mPresenter.onTapAddNewContactButton()
        }

        binding.btnAddGroup.setOnClickListener {
            mPresenter.onTapAddNewGroupButton()
        }
    }

    private fun setUpViewPod() {
        mViewPod = binding.rvContactGroup.root
        mViewPod.setUpContactGroupViewPod(mPresenter, mPresenter)

        mPresenter.getContacts(mPresenter.getUserId())
    }

    private fun setUpRecyclerView() {
        mAdapter = GroupAdapter(mPresenter)
        binding.rvGroupList.adapter = mAdapter
        binding.rvGroupList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun navigateToNewContactScreen() {
        startActivity(context?.let { AddNewContactActivity.newIntent(it) })
    }

    override fun navigateToNewGroupScreen() {
        startActivity(context?.let { AddNewGroupActivity.newIntent(it) })
    }

    override fun navigateToChatDetailScreen(userId: String) {
        startActivity(context?.let { ChatDetailActivity.newIntent(it) })
    }

    private fun getAlphabetList(nameList:List<String>) : List<Char> {
        val nameMapList = nameList.groupBy { it[0] }
        val alphabetList = arrayListOf<Char>()
        for(key in nameMapList.keys) {
            alphabetList.add(key)
        }
        return alphabetList.sorted()
    }

    override fun showContacts(contactList: List<UserVO>) {
        val nameList = arrayListOf<String>()
        for (contact in contactList){
            nameList.add(0, contact.userName)
        }
        mViewPod.setNewData(getAlphabetList(nameList), contactList, false)

    }

    override fun addUserToGroup(userId: String) {}
    @SuppressLint("SetTextI18n")
    override fun getGroupList(groupList: List<GroupVO>) {
        val mGroupList = arrayListOf<GroupVO>()
        for (group in groupList) {
            if (mPresenter.getUserId() in group.userIdList) {
                mGroupList.add(group)
            }
        }
        mAdapter.setNewData(mGroupList)
        val count  = mGroupList.size.toString()
        binding.tvGroupCount.text = "Groups($count)"
    }

    override fun showError(error: String) {
        Toast.makeText(requireActivity(),error, Toast.LENGTH_SHORT).show()
    }

}