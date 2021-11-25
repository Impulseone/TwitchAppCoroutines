package com.mycorp.common.fragment

import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.mycorp.common.Data
import com.mycorp.common.TCommand
import com.mycorp.common.Text

interface FragmentScene {
    val self: Fragment
    fun<T> LifecycleOwner.bindCommand(liveEvent: TCommand<T>, action: (T) -> Unit) {
        liveEvent.observe(self.viewLifecycleOwner, {
            if (it != null) {
                action(it)
            }
        })
    }
    fun LifecycleOwner.bindText(liveData: Text<String>, textView: TextView) {
        liveData.observe(self.viewLifecycleOwner, { textView.text = liveData.value })
    }
    fun<T> LifecycleOwner.bindData(liveData: Data<T>, action: (T) -> Unit) {
        liveData.observe(self.viewLifecycleOwner, { action(it) })
    }
}