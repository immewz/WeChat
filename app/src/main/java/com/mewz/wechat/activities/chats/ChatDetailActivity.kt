package com.mewz.wechat.activities.chats

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mewz.wechat.activities.MainActivity
import com.mewz.wechat.adapters.MessageAdapter
import com.mewz.wechat.databinding.ActivityChatDetailBinding

class ChatDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatDetailBinding

    private lateinit var mMessageAdapter: MessageAdapter

    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context, ChatDetailActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.btnChatDetailBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpRecyclerView() {
        mMessageAdapter = MessageAdapter()
        binding.rvRecipientMessage.adapter = mMessageAdapter
        binding.rvRecipientMessage.layoutManager =
            LinearLayoutManager(this@ChatDetailActivity)
    }
}