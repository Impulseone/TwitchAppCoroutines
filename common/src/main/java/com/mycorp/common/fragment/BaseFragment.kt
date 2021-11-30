package com.mycorp.common.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mycorp.common.R
import com.mycorp.common.viewModel.BaseViewModel
import com.mycorp.navigation.BaseNavigationFlow
import com.mycorp.navigation.ToFlowNavigatable

@SuppressLint("ResourceType")
abstract class BaseFragment<VM : BaseViewModel>(layoutId: Int) : Fragment(layoutId), FragmentScene {
    abstract val viewModel: VM
    abstract val navigationFlow: BaseNavigationFlow

    private val flowNavigatable by lazy {
        requireActivity() as ToFlowNavigatable
    }

    override val self: Fragment
        get() = this

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        flowNavigatable.popBackStack(navigationFlow, this.viewLifecycleOwner)
    }

    open fun bindVm() {
        with(viewModel) {
            bindData(showToast) {
                if (it != null) {
                    val (text, length) = it
                    Toast.makeText(requireContext(), text, length).show()
                }
            }
            bindCommand(openScreenCommand) {
                flowNavigatable.navigateToFlow(it)
            }
            bindCommand(connectionExceptionCommand) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.scr_base_fragment_connection_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}



