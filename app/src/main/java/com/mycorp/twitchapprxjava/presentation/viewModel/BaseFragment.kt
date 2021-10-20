package com.mycorp.twitchapprxjava.presentation.viewModel

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.fragment.app.Fragment

@SuppressLint("ResourceType")
abstract class BaseFragment<VM : BaseViewModel> : Fragment(123345) {
    abstract val viewModel: VM

    open fun bindVm() {
        viewModel.showToast.observe(this, {
            Toast.makeText(requireContext(), it!!.first, it.second).show()
        })
    }

}



