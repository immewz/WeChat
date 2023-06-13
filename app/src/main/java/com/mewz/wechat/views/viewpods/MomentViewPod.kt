package com.mewz.wechat.views.viewpods

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.mewz.wechat.adapters.MomentAdapter
import com.mewz.wechat.data.vos.MyMomentVO
import com.mewz.wechat.databinding.ViewPodMomentBinding
import com.mewz.wechat.delegtes.MomentItemViewHolderDelegate

class MomentViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    private lateinit var binding: ViewPodMomentBinding
    private lateinit var mAdapter: MomentAdapter

    private lateinit var mDelegate: MomentItemViewHolderDelegate

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = ViewPodMomentBinding.bind(this)

    }

    fun setUpMomentViewPod(delegate: MomentItemViewHolderDelegate){
        this.mDelegate = delegate
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        mAdapter = MomentAdapter(mDelegate)
        binding.rvMomentList.adapter = mAdapter
        binding.rvMomentList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    fun setNewData(momentList: List<MyMomentVO>) {
        mAdapter.setNewData(momentList)
    }
}