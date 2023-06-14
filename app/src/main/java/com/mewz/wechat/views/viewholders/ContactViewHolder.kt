package com.mewz.wechat.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.databinding.ViewHolderContactBinding
import com.mewz.wechat.delegtes.ChatItemViewHolderDelegate

class ContactViewHolder(itemView: View, private val delegate: ChatItemViewHolderDelegate
) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderContactBinding
    private var mUser:UserVO? = null

    init {
        binding = ViewHolderContactBinding.bind(itemView)

        itemView.setOnClickListener {
            delegate.onTapChatItem(mUser?.userId ?: "")
        }
    }

    fun bindData(user: UserVO, isGroup: Boolean) {
        mUser = user
        binding.tvContactName.text = user.userName

        Glide.with(itemView.context)
            .load(user.imageUrl)
            .into(binding.ivContactProfileImage)

        if(isGroup) {
            binding.cbAddGroup.visibility = View.VISIBLE

            binding.cbAddGroup.setOnCheckedChangeListener{_, isChecked ->
                if (isChecked){
                    delegate.onTapCheckbox(user.userId, true)
                }else{
                    delegate.onTapCheckbox(user.userId, false)
                }
            }
        } else {
            binding.cbAddGroup.visibility = View.GONE
        }
    }
}