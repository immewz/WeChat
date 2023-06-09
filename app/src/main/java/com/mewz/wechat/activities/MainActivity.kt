package com.mewz.wechat.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mewz.wechat.R
import com.mewz.wechat.activities.login.SplashActivity
import com.mewz.wechat.databinding.ActivityMainBinding
import com.mewz.wechat.fragments.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpBottomNavigationView()
    }

    private fun setUpBottomNavigationView() {
        switchFragment(MomentFragment())

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.fragmentMoment -> {
                    switchFragment(MomentFragment())
                    true
                }
                R.id.fragmentChat -> {
                    switchFragment(ChatFragment())
                    true
                }
                R.id.fragmentContact -> {
                    switchFragment(ContactFragment())
                    true
                }
                R.id.fragmentProfile -> {
                    switchFragment(ProfileFragment())
                    true
                }
                R.id.fragmentSetting -> {
                    switchFragment(SettingFragment())
                    true
                }
                else -> false

            }
        }
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer,fragment)
            .commit()
    }
}