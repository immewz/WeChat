package com.mewz.wechat.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mewz.wechat.data.vos.MessageVO
import com.mewz.wechat.data.vos.PrivateMessageVO
import com.mewz.wechat.databinding.ViewHolderRecipientBinding
import java.text.SimpleDateFormat
import java.util.*

class RecipientViewHolder(itemView: View) : BaseMessageViewHolder(itemView) {

    private var binding: ViewHolderRecipientBinding

    init {
        binding = ViewHolderRecipientBinding.bind(itemView)
    }

    override fun bindData(message: PrivateMessageVO) {
        Glide.with(itemView.context)
            .load(message.userProfileImage)
            .into(binding.ivChatDetailProfileImage)

        if (message.message.isEmpty()){
            binding.cvChatMessage.visibility = View.GONE
            binding.llChatImage.visibility = View.VISIBLE

            Glide.with(itemView.context)
                .load(message.file)
                .into(binding.ivRecipientMessageImage)

            binding.tvRecipientMessageTimeImage.text = getCurrentHourAndMinutes(message.timeStamp)

        }else if (message.message.isNotEmpty() && message.file.isEmpty()){
            binding.cvChatMessage.visibility = View.VISIBLE
            binding.llChatImage.visibility = View.GONE

            binding.tvRecipientMessage.text = message.message
            binding.tvRecipientMessageTime.text = getCurrentHourAndMinutes(message.timeStamp)

        }else{
            binding.cvChatMessage.visibility = View.VISIBLE
            binding.llChatImage.visibility = View.VISIBLE

            binding.tvRecipientMessage.text = message.message
            binding.tvRecipientMessageTime.text = getCurrentHourAndMinutes(message.timeStamp)

            Glide.with(itemView.context)
                .load(message.file)
                .into(binding.ivRecipientMessageImage)
        }
    }

    private fun getCurrentHourAndMinutes(currentTimeMillis:Long) :String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = currentTimeMillis

        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val minutes = calendar.get(Calendar.MINUTE)

        val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return timeFormat.format(calendar.time)
    }
}