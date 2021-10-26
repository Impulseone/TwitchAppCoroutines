package com.mycorp.twitchapprxjava.presentation.fragments

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mycorp.twitchapprxjava.data.storage.model.FollowerInfo

class FollowersListAdapter: ListAdapter<FollowerInfo, FollowerItemView>(FollowersListChangesCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FollowerItemView.from(parent)

    override fun onBindViewHolder(holder: FollowerItemView, position: Int) {
        holder.bind(currentList[position])
    }
}