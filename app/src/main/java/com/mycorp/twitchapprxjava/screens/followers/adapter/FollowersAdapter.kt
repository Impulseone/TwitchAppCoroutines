package com.mycorp.twitchapprxjava.screens.followers.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mycorp.twitchapprxjava.models.FollowerInfo
import com.mycorp.twitchapprxjava.common.helpers.BaseItemCallback
import com.mycorp.twitchapprxjava.models.ListItemData

class FollowersAdapter: ListAdapter<ListItemData<FollowerInfo>, FollowerViewHolder>(BaseItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FollowerViewHolder.from(parent)

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.bind(currentList[position].data)
    }
}