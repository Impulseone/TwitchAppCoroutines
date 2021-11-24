package com.mycorp.twitchapprxjava.screens.followers.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mycorp.model.FollowerInfo
import com.mycorp.model.ListItemData
import com.mycorp.twitchapprxjava.common.helpers.BaseItemCallback

class FollowersAdapter: ListAdapter<ListItemData<FollowerInfo>, FollowerViewHolder>(BaseItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FollowerViewHolder.from(parent)

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.bind(currentList[position].data)
    }
}