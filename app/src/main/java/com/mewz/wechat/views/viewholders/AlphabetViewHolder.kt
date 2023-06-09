package com.mewz.wechat.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.databinding.ViewHolderAlphabetBinding

class AlphabetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderAlphabetBinding

    init {
        binding = ViewHolderAlphabetBinding.bind(itemView)
    }

    fun bindData(alphabet: Any) {
        binding.tvAlphabet.text = alphabet.toString()
    }
}