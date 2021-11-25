package com.mycorp.features.screens.followers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mycorp.features.databinding.ItemFollowerBinding
import com.mycorp.model.FollowerInfo

class FollowerViewHolder(private val binding: ItemFollowerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(followerInfo: FollowerInfo) {
        with(binding) {
            Glide.with(itemView.context).load(followerInfo.photoUrl).into(followerPhoto)
            followerName.text = followerInfo.followerName
        }
    }

    companion object {
        fun from(parent: ViewGroup): FollowerViewHolder {
            return FollowerViewHolder(
                ItemFollowerBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}