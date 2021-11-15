package com.mycorp.twitchapprxjava.screens.followers.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mycorp.twitchapprxjava.model.FollowerInfo
import com.mycorp.twitchapprxjava.common.helpers.BaseItemCallback

class FollowersAdapter: ListAdapter<FollowerInfo, FollowerViewHolder>(BaseItemCallback<FollowerInfo>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FollowerViewHolder.from(parent)

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}