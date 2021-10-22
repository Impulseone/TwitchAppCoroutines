package com.mycorp.twitchapprxjava.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.databinding.FragmentGamesListBinding
import com.mycorp.twitchapprxjava.presentation.gamesListView.GamesListAdapter
import com.mycorp.twitchapprxjava.presentation.gamesListView.RecyclerTouchListener
import com.mycorp.twitchapprxjava.presentation.gamesListView.RecyclerTouchListener.ClickListener
import com.mycorp.twitchapprxjava.presentation.viewModel.BaseFragment
import com.mycorp.twitchapprxjava.presentation.viewModel.GamesListVM
import org.koin.androidx.viewmodel.ext.android.viewModel


class GamesListFragment : BaseFragment<GamesListVM>(R.layout.fragment_games_list) {

    private val binding: FragmentGamesListBinding by viewBinding()
    private var gamesListAdapter: GamesListAdapter? = null
    override val viewModel: GamesListVM by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindVm()
    }

    override fun bindVm() {
        super.bindVm()
        viewModel.gamesLiveData().observe(viewLifecycleOwner, {
            changeProgressbarVisibility(it.progressIndicatorVisibility)
            gamesListAdapter?.submitList(it.data)
        })
    }

    private fun initViews() {
        with(binding) {
            gamesListAdapter = GamesListAdapter()
            gamesRv.layoutManager =
                LinearLayoutManager(context)
            gamesRv.adapter = gamesListAdapter
            gamesRv.addOnItemTouchListener(
                RecyclerTouchListener(
                    context,
                    gamesRv,
                    object : ClickListener {
                        override fun onClick(view: View?, position: Int) {
                            findNavController().navigate(R.id.gameItemFragment)
                        }

                        override fun onLongClick(view: View?, position: Int) {
                            findNavController().navigate(R.id.gameItemFragment)
                        }
                    })
            )

            reportButton.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_gamesListFragment_to_ratingFragment)
            )
        }
    }

    private fun changeProgressbarVisibility(visibility: Boolean) {
        binding.progressIndicator.isVisible = visibility
    }

}