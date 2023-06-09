package com.mewz.wechat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mewz.wechat.R
import com.mewz.wechat.databinding.FragmentSettingBinding


class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_setting, container, false)

        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }


}