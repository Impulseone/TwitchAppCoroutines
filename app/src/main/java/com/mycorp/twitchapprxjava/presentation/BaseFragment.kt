package com.mycorp.twitchapprxjava.presentation

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import java.util.concurrent.atomic.AtomicBoolean

abstract class FooBaseViewModel : ViewModel() {

    val showToast = SingleLiveEvent<Pair<String, Int>>()

    fun showToast(text: String, length: Int = Toast.LENGTH_SHORT) {
        showToast.value = text to length
    }
}

@SuppressLint("ResourceType")
abstract class FooBaseFragment<VM : FooBaseViewModel> : Fragment(123345) {
    abstract val viewModel: VM

    open fun bindVm() {
        viewModel.showToast.observe(this, {
            Toast.makeText(requireContext(), it!!.first, it.second).show()
        })
    }

}


class SingleLiveEvent<T> : MutableLiveData<T?>() {
    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T?>) {
        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
        }
        // Observe the internal MutableLiveData
        super.observe(owner,
            { t: T? ->
                if (mPending.compareAndSet(true, false)) {
                    observer.onChanged(t)
                }
            })
    }

    @MainThread
    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        value = null
    }

    companion object {
        private const val TAG = "SingleLiveEvent"
    }
}



