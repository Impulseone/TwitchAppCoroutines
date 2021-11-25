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

@SuppressLint("ResourceType")
abstract class BaseFragment<VM : BaseViewModel>(layoutId: Int) : Fragment(layoutId), FragmentScene {
    abstract val viewModel: VM

    override val self: Fragment
        get() = this

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //TODO: fix this
//        when (this) {
//            is GamesFragment -> closeApp()
//            is FavoriteGamesFragment -> closeApp()
//        }
    }

    open fun bindVm() {
        with(viewModel){
            bindData(showToast) {
                if (it != null) {
                    val (text, length) = it
                    Toast.makeText(requireContext(), text, length).show()
                }
            }
            bindCommand(openScreenCommand) {
                findNavController().navigate(it)
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

    private fun closeApp() {
        with(requireActivity()) {
            onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                finish()
            }
        }
    }
}



