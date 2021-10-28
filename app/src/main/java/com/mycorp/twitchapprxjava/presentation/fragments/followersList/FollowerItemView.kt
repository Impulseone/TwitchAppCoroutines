package com.mycorp.twitchapprxjava.presentation.fragments.followersList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycorp.twitchapprxjava.GlideApp
import com.mycorp.twitchapprxjava.data.storage.model.FollowerInfo
import com.mycorp.twitchapprxjava.databinding.FollowerItemViewBinding

class FollowerItemView(private val binding: FollowerItemViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(followerInfo: FollowerInfo) {
        with(binding) {
            GlideApp.with(itemView.context).load(followerInfo.photoUrl).into(followerPhoto)
            followerName.text = followerInfo.followerName
        }
    }

    companion object {
        fun from(parent: ViewGroup): FollowerItemView {
            return FollowerItemView(
                FollowerItemViewBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}