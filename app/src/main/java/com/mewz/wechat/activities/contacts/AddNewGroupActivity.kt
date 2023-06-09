package com.mewz.wechat.activities.contacts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mewz.wechat.R
import com.mewz.wechat.adapters.AlphabetAdapter
import com.mewz.wechat.adapters.ContactGroupAdapter
import com.mewz.wechat.adapters.MemberAdapter
import com.mewz.wechat.databinding.ActivityAddNewGroupBinding
import com.mewz.wechat.utils.DummyData

class AddNewGroupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNewGroupBinding

    private lateinit var mAdapter: MemberAdapter
    private lateinit var mContactAdapter: ContactGroupAdapter
    private lateinit var mAlphabetAdapter: AlphabetAdapter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, AddNewGroupActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewGroupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.btnDismiss.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpRecyclerView() {
        mAdapter = MemberAdapter()
        binding.rvMemberList.adapter = mAdapter
        binding.rvMemberList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        mContactAdapter = ContactGroupAdapter()
        binding.rvContactGroupList.adapter = mContactAdapter
        binding.rvContactGroupList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mAlphabetAdapter = AlphabetAdapter(DummyData.getAlphabetList())
        binding.rvAlphabetList.adapter = mAlphabetAdapter
        binding.rvAlphabetList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}