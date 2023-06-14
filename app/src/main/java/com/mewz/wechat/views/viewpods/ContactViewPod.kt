package com.mewz.wechat.views.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.mewz.wechat.adapters.AlphabetAdapter
import com.mewz.wechat.adapters.ContactGroupAdapter
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.databinding.ViewPodContactBinding
import com.mewz.wechat.delegtes.AlphabetActionIItemDelegate
import com.mewz.wechat.delegtes.ChatItemViewHolderDelegate
import com.mewz.wechat.utils.DummyData

class ContactViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    private lateinit var binding: ViewPodContactBinding

    private lateinit var mAlphaAdapter: AlphabetAdapter
    private lateinit var mContactGroupAdapter: ContactGroupAdapter

    private lateinit var mDelegateChat: ChatItemViewHolderDelegate
    private lateinit var mDelegateAlphabet: AlphabetActionIItemDelegate

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = ViewPodContactBinding.bind(this)
    }

    fun setUpContactGroupViewPod(delegateChat: ChatItemViewHolderDelegate, delegateAlphabet: AlphabetActionIItemDelegate){
        this.mDelegateChat = delegateChat
        this.mDelegateAlphabet = delegateAlphabet
        setUpAlphaAdapter()
        setUpContactGroupAdapter()
    }

    private fun setUpContactGroupAdapter() {
        mContactGroupAdapter = ContactGroupAdapter(mDelegateChat)
        binding.rvContactGroupList.adapter = mContactGroupAdapter
        binding.rvContactGroupList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpAlphaAdapter() {
        mAlphaAdapter = AlphabetAdapter(DummyData.getAlphabetList(), mDelegateAlphabet)
        binding.rvAlphabetList.adapter = mAlphaAdapter
        binding.rvAlphabetList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    fun setNewData(alphabetList: List<Char>, contactList: List<UserVO>, isGroup: Boolean) {
        mContactGroupAdapter.setNewData(alphabetList, contactList , isGroup)
    }
}