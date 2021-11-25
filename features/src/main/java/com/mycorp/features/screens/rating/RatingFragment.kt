package com.mycorp.features.screens.rating

import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycorp.common.fragment.BaseFragment
import com.mycorp.features.R
import com.mycorp.features.databinding.FragmentRatingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DecimalFormat

class RatingFragment : BaseFragment<RatingViewModel>(R.layout.fragment_rating) {

    private val binding: FragmentRatingBinding by viewBinding()
    override val viewModel: RatingViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindVm()
    }

    private fun initViews() {
        with(binding) {
            ratingBar.onRatingBarChangeListener =
                RatingBar.OnRatingBarChangeListener { _, rating, _ ->
                    viewModel.updateRating(getString(R.string.scr_rating_toast_text, DecimalFormat("#0.0").format(rating)))
                }
            sendReportBtn.setOnClickListener {
                findNavController().navigate(RatingFragmentDirections.actionRatingFragmentToGamesFragment())
            }
            backBtn.setOnClickListener {
                findNavController().navigate(RatingFragmentDirections.actionRatingFragmentToGamesFragment())
            }
        }
    }
}