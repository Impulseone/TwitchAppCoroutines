package com.mycorp.twitchapprxjava.presentation

import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.databinding.FragmentRatingBinding
import com.mycorp.twitchapprxjava.presentation.viewModel.BaseFragment
import com.mycorp.twitchapprxjava.presentation.viewModel.RatingVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class RatingFragment : BaseFragment<RatingVM>(R.layout.fragment_rating) {

    private val binding: FragmentRatingBinding by viewBinding()

    override val viewModel: RatingVM by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindVm()
    }

    override fun onStart() {
        super.onStart()
        with(binding) {
            ratingBarDefault.onRatingBarChangeListener =
                RatingBar.OnRatingBarChangeListener { _, rating, _ ->
                    viewModel.showToast(text = "rating: $rating")
                }

            sendReportBtn.setOnClickListener {
                findNavController().navigate(R.id.gamesListFragment)
            }
            backBtn.setOnClickListener {
                findNavController().navigate(R.id.gamesListFragment)
            }
        }
    }
}