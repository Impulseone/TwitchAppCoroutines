package com.mycorp.twitchapprxjava.presentation.viewModel

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.fragment.app.Fragment

@SuppressLint("ResourceType")
abstract class BaseFragment<VM : BaseViewModel>(layoutId: Int) : Fragment(layoutId) {
    abstract val viewModel: VM

    open fun bindVm() {
        viewModel.showToast.observe(this, {
            if (it == null) return@observe
            val (text, length) = it

            Toast.makeText(requireContext(), text, length).show()
        })
    }

}



