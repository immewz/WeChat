package com.mewz.wechat.views.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.mewz.wechat.adapters.MomentAdapter
import com.mewz.wechat.databinding.ViewPodMomentBinding

class MomentViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    private lateinit var binding: ViewPodMomentBinding
    private lateinit var mAdapter: MomentAdapter

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = ViewPodMomentBinding.bind(this)

    }

    fun setUpMomentViewPod(){
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        mAdapter = MomentAdapter()
        binding.rvMomentList.adapter = mAdapter
        binding.rvMomentList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}