package com.mycorp.twitchapprxjava.common.extensions

import android.graphics.Rect
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setIgnoreLastDivider(@DrawableRes drawableId: Int) {
    val divider = object : DividerItemDecoration(context, VERTICAL) {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view)
            if (position == state.itemCount - 1) {
                outRect.setEmpty()
            } else {
                super.getItemOffsets(outRect, view, parent, state)
            }
        }
    }

    ContextCompat.getDrawable(context, drawableId)?.let {
        divider.setDrawable(it)
    }

    addItemDecoration(divider)
}