package com.mycorp.twitchapprxjava.common.helpers

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class BaseItemCallback<T> : DiffUtil.ItemCallback<ItemData<T>>() {
    override fun areItemsTheSame(oldItem: ItemData<T>, newItem: ItemData<T>): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ItemData<T>, newItem: ItemData<T>): Boolean {
        return oldItem.data == newItem.data
    }
}

class ItemData<T>(val id: String, val data: T)