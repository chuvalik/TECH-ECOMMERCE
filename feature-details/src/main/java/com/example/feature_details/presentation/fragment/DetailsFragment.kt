package com.example.feature_details.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.bumptech.glide.RequestManager
import com.example.core.extension.showSnackBar
import com.example.core.ui.BaseFragment
import com.example.feature_details.databinding.FragmentDetailsBinding
import com.example.feature_details.presentation.adapter.DetailsViewPagerAdapter
import com.example.feature_details.presentation.model.DetailsEvent
import com.example.feature_details.presentation.model.DetailsSideEffect
import com.example.feature_details.presentation.model.DetailsState
import com.example.feature_details.presentation.view_model.DetailsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import kotlin.math.abs


class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    private val viewModel by stateViewModel<DetailsViewModel>(state = {
        val bundle = Bundle()
        bundle.putString("id", arguments?.getString("id"))
        bundle
    })
    private val glide by inject<RequestManager>()

    private var adapter: DetailsViewPagerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        processUiEvent()

        setupViewPager()

        observeUiEffect()
        observeUiState()
    }

    private fun processUiEvent() {
        binding.btnGoBack.setOnClickListener { viewModel.onEvent(DetailsEvent.BackButtonClicked) }

        binding.cvAddToCart.setOnClickListener { viewModel.onEvent(DetailsEvent.AddToCartButtonClicked) }

        binding.btnFavorites.setOnClickListener { viewModel.onEvent(DetailsEvent.AddToFavoriteButtonClicked) }
    }

    private fun setupViewPager() {
        adapter = DetailsViewPagerAdapter(glide).also { adapter ->
            binding.viewPager.adapter = adapter
        }

        binding.viewPager.offscreenPageLimit = 3
        binding.viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val pageTransformer = CompositePageTransformer()
        pageTransformer.addTransformer(MarginPageTransformer(30))
        pageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85F + r * 0.25F
        }

        binding.viewPager.setPageTransformer(pageTransformer)

        binding.circleIndicator.attachTo(binding.viewPager)
    }


    private fun observeUiEffect() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is DetailsSideEffect.NavigateBack -> findNavController().popBackStack()
                is DetailsSideEffect.ShowSnackbar -> requireContext().showSnackBar(
                    binding.root,
                    effect.message.asString(requireContext()),
                )
            }
        }
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                processUiState(state)
            }
        }
    }

    private fun processUiState(state: DetailsState) {
        adapter?.submitList(state.model.data.images)

        binding.tvTitle.text = state.model.data.modelFull
        binding.tvPrice.text =
            getString(com.example.ui_component.R.string.formatted_price, state.model.data.price)
        binding.tvDescription.text = state.model.data.description
        binding.ratingBar.rating = state.model.data.rating

        val favoriteIcon = if (state.model.isFavorites)
            com.example.ui_component.R.drawable.ic_favorite_filled
        else
            com.example.ui_component.R.drawable.ic_favorite

        binding.btnFavorites.setImageResource(favoriteIcon)
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailsBinding.inflate(inflater, container, false)
}