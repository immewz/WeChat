package com.mewz.wechat.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mewz.wechat.R
import com.mewz.wechat.views.viewholders.MomentImagesViewHolder

class MomentImageAdapter : RecyclerView.Adapter<MomentImagesViewHolder>() {

    private var mImageList:List<String> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MomentImagesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_moment_image_list,parent,false)
        return MomentImagesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MomentImagesViewHolder, position: Int) {
        holder.bindNewData(mImageList[position])
    }

    override fun getItemCount(): Int {
        return mImageList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(imageList:List<String>) {
        mImageList = imageList
        Log.i("ImageListAth",mImageList.toString())
        notifyDataSetChanged()
    }
}