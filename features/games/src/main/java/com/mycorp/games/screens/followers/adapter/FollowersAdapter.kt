package com.mycorp.games.screens.followers.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mycorp.common.helpers.BaseItemCallback
import com.mycorp.model.FollowerInfo
import com.mycorp.model.ListItemData

class FollowersAdapter: ListAdapter<ListItemData<FollowerInfo>, FollowerViewHolder>(BaseItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FollowerViewHolder.from(parent)

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.bind(currentList[position].data)
    }
}