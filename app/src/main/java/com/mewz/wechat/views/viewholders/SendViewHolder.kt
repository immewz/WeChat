package com.mewz.wechat.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.mewz.wechat.data.vos.MessageVO
import com.mewz.wechat.databinding.ViewHolderSendBinding
import java.text.SimpleDateFormat
import java.util.*

class SendViewHolder(itemView: View) : BaseMessageViewHolder(itemView) {

    private var binding: ViewHolderSendBinding

    init {
        binding = ViewHolderSendBinding.bind(itemView)
    }

    override fun bindData(message: MessageVO) {

        if (message.message.isEmpty() && message.file.isNotEmpty()){
            binding.cvChatMessage.visibility = View.GONE
            binding.llChatImage.visibility = View.VISIBLE

            Glide.with(itemView.context)
                .load(message.file)
                .into(binding.ivSenderMessageImage)

            binding.tvSenderMessageTimeImage.text = getCurrentHourAndMinutes(message.timeStamp)

        }else if (message.message.isNotEmpty() && message.file.isEmpty()){
            binding.cvChatMessage.visibility = View.VISIBLE
            binding.llChatImage.visibility = View.GONE

            binding.tvSenderMessage.text = message.message
            binding.tvSenderMessageTime.text = getCurrentHourAndMinutes(message.timeStamp)

        }else{
            binding.cvChatMessage.visibility = View.VISIBLE
            binding.llChatImage.visibility = View.VISIBLE

            binding.tvSenderMessage.text = message.message
            binding.tvSenderMessage.text = getCurrentHourAndMinutes(message.timeStamp)

            Glide.with(itemView.context)
                .load(message.file)
                .into(binding.ivSenderMessageImage)
        }
    }

    private fun getCurrentHourAndMinutes(currentTimeMillis:Long) :String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = currentTimeMillis

        val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return timeFormat.format(calendar.time)
    }
}