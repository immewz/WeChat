package com.mewz.wechat.activities.contacts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mewz.wechat.R
import com.mewz.wechat.databinding.ActivityAddNewContactBinding

class AddNewContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNewContactBinding

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, AddNewContactActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpListeners()
    }

    private fun setUpListeners() {
        binding.btnAddContactBack.setOnClickListener {
            onBackPressed()
        }
    }
}