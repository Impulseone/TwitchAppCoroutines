package com.mycorp.twitchapprxjava.presentation.fragments.followersList

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mycorp.twitchapprxjava.database.model.FollowerInfo
import com.mycorp.twitchapprxjava.presentation.fragments.BaseItemCallback

class FollowersListAdapter: ListAdapter<FollowerInfo, FollowerItemView>(BaseItemCallback<FollowerInfo>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FollowerItemView.from(parent)

    override fun onBindViewHolder(holder: FollowerItemView, position: Int) {
        holder.bind(currentList[position])
    }
}