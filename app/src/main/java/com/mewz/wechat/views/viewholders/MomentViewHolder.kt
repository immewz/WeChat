package com.mewz.wechat.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.mewz.wechat.R
import com.mewz.wechat.adapters.MomentAdapter
import com.mewz.wechat.adapters.MomentImageAdapter
import com.mewz.wechat.adapters.NewMomentImageAdapter
import com.mewz.wechat.data.vos.MyMomentVO
import com.mewz.wechat.databinding.ViewHolderMomentBinding
import com.mewz.wechat.delegtes.MomentItemViewHolderDelegate
import com.mewz.wechat.delegtes.NewMomentImageViewHolderDelegate

class MomentViewHolder(itemView: View, private val delegate: MomentItemViewHolderDelegate)
    : AbstractBaseViewHolder<MyMomentVO>(itemView) {

    private var binding: ViewHolderMomentBinding
    private lateinit var mAdapter: MomentImageAdapter
    private var mMoment: MyMomentVO? = null

    init {
        binding = ViewHolderMomentBinding.bind(itemView)

        binding.btnBookMark.setOnClickListener {
            if (mMoment?.isBookmarked == true) {
                delegate.onTapBookmarkButton(mMoment?.id ?: "", false)
            }else{
                delegate.onTapBookmarkButton(mMoment?.id ?: "", true)
            }
        }
    }

    override fun bindData(data: MyMomentVO) {
        mMoment = data

        binding.tvProfileName.text = data.userName
        binding.tvCaption.text = data.caption

        Glide.with(itemView.context)
            .load(data.userProfileImage)
            .into(binding.ivProfileImage)

        setUpMomentImages()
        mAdapter.setNewData(changeImageStringToList(data.imageUrl))

        if (data.isBookmarked){
            binding.btnBookMark.setImageResource(R.drawable.bookmark_red)
        }else{
            binding.btnBookMark.setImageResource(R.drawable.bookmark)
        }
    }

    private fun changeImageStringToList(imageString:String) : List<String> {
        return imageString.split(',').toList()
    }

    private fun setUpMomentImages() {
        mAdapter = MomentImageAdapter()
        binding.viewPagerImage.adapter = mAdapter
    }

}