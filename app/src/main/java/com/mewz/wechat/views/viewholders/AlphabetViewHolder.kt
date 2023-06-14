package com.mewz.wechat.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.databinding.ViewHolderAlphabetBinding
import com.mewz.wechat.delegtes.AlphabetActionIItemDelegate

class AlphabetViewHolder(itemView: View, private val delegate: AlphabetActionIItemDelegate)
    : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderAlphabetBinding

    init {
        binding = ViewHolderAlphabetBinding.bind(itemView)
    }

    fun bindData(alphabet: Any) {
        binding.tvAlphabet.text = alphabet.toString()
    }
}