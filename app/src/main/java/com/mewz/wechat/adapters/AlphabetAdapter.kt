package com.mewz.wechat.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.R
import com.mewz.wechat.delegtes.AlphabetActionIItemDelegate
import com.mewz.wechat.views.viewholders.AlphabetViewHolder

class AlphabetAdapter(
    private val alphabetList: List<Char>,
    private val delegate: AlphabetActionIItemDelegate
): RecyclerView.Adapter<AlphabetViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlphabetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_alphabet,parent,false)
        return AlphabetViewHolder(view, delegate)
    }

    override fun getItemCount(): Int {
        return alphabetList.size
    }

    override fun onBindViewHolder(holder: AlphabetViewHolder, position: Int) {
        holder.bindData(alphabetList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(alphabetList: List<Char>) {

        notifyDataSetChanged()
    }
}