package com.mycorp.twitchapprxjava.common.helpers

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.mycorp.twitchapprxjava.models.ListItemData

class BaseItemCallback<T> : DiffUtil.ItemCallback<ListItemData<T>>() {
    override fun areItemsTheSame(oldItem: ListItemData<T>, newItem: ListItemData<T>): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ListItemData<T>, newItem: ListItemData<T>): Boolean {
        return oldItem.data == newItem.data
    }
}