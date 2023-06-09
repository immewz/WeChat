package com.mewz.wechat.fragments

import android.os.Bundle
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
import com.mewz.wechat.databinding.FragmentContactBinding
import com.mewz.wechat.delegtes.ChatItemViewHolderDelegate
import com.mewz.wechat.delegtes.GroupItemViewHolderDelegate
import com.mewz.wechat.mvp.presenters.ContactPresenter
import com.mewz.wechat.mvp.presenters.impls.ContactPresenterImpl
import com.mewz.wechat.mvp.views.ContactView
import com.mewz.wechat.utils.DummyData

class ContactFragment : Fragment(), ContactView {

    private lateinit var binding: FragmentContactBinding

    private lateinit var mAdapter: GroupAdapter
    private lateinit var mContactAdapter: ContactGroupAdapter
    private lateinit var mAlphabetAdapter: AlphabetAdapter

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

    private fun setUpRecyclerView() {
        mAdapter = GroupAdapter(mPresenter)
        binding.rvGroupList.adapter = mAdapter
        binding.rvGroupList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        mContactAdapter = ContactGroupAdapter()
        binding.rvContactGroupList.adapter = mContactAdapter
        binding.rvContactGroupList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        mAlphabetAdapter = AlphabetAdapter(DummyData.getAlphabetList())
        binding.rvAlphabetList.adapter = mAlphabetAdapter
        binding.rvAlphabetList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


    }

    override fun navigateToNewContactScreen() {
        startActivity(context?.let { AddNewContactActivity.newIntent(it) })
    }

    override fun navigateToNewGroupScreen() {
        startActivity(context?.let { AddNewGroupActivity.newIntent(it) })
    }

    override fun navigateToChatDetailScreen() {
        startActivity(context?.let { ChatDetailActivity.newIntent(it) })
    }

    override fun showError(error: String) {
        Toast.makeText(requireActivity(),error, Toast.LENGTH_SHORT).show()
    }

}