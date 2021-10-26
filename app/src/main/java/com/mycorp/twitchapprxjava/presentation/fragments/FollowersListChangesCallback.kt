package com.mycorp.twitchapprxjava.presentation.fragments

import androidx.recyclerview.widget.DiffUtil
import com.mycorp.twitchapprxjava.data.storage.model.FollowerInfo

class FollowersListChangesCallback: DiffUtil.ItemCallback<FollowerInfo>() {
    override fun areItemsTheSame(oldItem: FollowerInfo, newItem: FollowerInfo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: FollowerInfo, newItem: FollowerInfo): Boolean {
        return oldItem == newItem
    }
}