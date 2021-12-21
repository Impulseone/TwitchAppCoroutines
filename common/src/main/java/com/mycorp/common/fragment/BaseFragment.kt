package com.mycorp.common.fragment

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mycorp.common.R
import com.mycorp.common.viewModel.BaseViewModel
import com.mycorp.navigation.ToFlowNavigatable

@SuppressLint("ResourceType")
abstract class BaseFragment<VM : BaseViewModel>(layoutId: Int) : Fragment(layoutId), FragmentScene {
    abstract val viewModel: VM

    private val flowNavigatable by lazy {
        requireActivity() as ToFlowNavigatable
    }

    override val self: Fragment
        get() = this

    open fun bindVm() {
        with(viewModel) {
            bindEvent(showToastEvent) {
                val (text, length) = it
                Toast.makeText(requireContext(), text, length).show()
            }
            bindEvent(openScreenEvent) {
                flowNavigatable.navigateToFlow(it.first, it.second)
            }
            bindEvent(connectionExceptionEvent) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.scr_base_fragment_connection_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}



