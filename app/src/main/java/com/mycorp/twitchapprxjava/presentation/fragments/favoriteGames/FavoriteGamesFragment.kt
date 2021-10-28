package com.mycorp.twitchapprxjava.presentation.fragments.favoriteGames

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.databinding.FragmentFavoriteGamesBinding
import com.mycorp.twitchapprxjava.presentation.viewModel.BaseFragment
import com.mycorp.twitchapprxjava.presentation.viewModel.FavoriteGamesVM
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteGamesFragment : BaseFragment<FavoriteGamesVM>(R.layout.fragment_favorite_games) {
    override val viewModel: FavoriteGamesVM by viewModel()
    private val binding: FragmentFavoriteGamesBinding by viewBinding()
    private var favoriteGamesListAdapter: FavoriteGamesListAdapter = FavoriteGamesListAdapter()
    private val mDisposable: CompositeDisposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGamesListAdapter()
        bindVm()
    }

    private fun initGamesListAdapter() {
        with(binding) {
            gamesRv.layoutManager =
                LinearLayoutManager(context)
            gamesRv.adapter = favoriteGamesListAdapter
        }
    }

    override fun bindVm() {
        super.bindVm()
        viewModel.gamesLiveData().observe(viewLifecycleOwner, {
            if (it.data != null)
                lifecycleScope.launch {
                    favoriteGamesListAdapter.submitData(it.data)
                }
        })
    }

    override fun onDestroyView() {
        mDisposable.dispose()
        super.onDestroyView()
    }
}