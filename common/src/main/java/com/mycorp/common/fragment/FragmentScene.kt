package com.mycorp.common.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.mycorp.common.extensions.collectFlow
import kotlinx.coroutines.flow.MutableSharedFlow

interface FragmentScene {
    val self: Fragment

    fun <T> LifecycleOwner.bindEvent(flowEvent: MutableSharedFlow<T>, action: (T) -> Unit) {
        self.collectFlow(flowEvent) {
            action(it)
        }
    }
}