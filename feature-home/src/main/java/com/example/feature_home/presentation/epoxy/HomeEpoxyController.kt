package com.example.feature_home.presentation.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.bumptech.glide.RequestManager
import com.example.feature_home.presentation.epoxy.model.ModelHomeItem
import com.example.feature_home.presentation.epoxy.model.ShimmerHomeItem
import com.example.feature_home.presentation.model.HomeState

class HomeEpoxyController(
    private val glide: RequestManager,
    private val onProductClick: (String) -> Unit
) : TypedEpoxyController<HomeState>() {

    override fun buildModels(state: HomeState?) {
        if (state?.isLoading == true) {
            repeat(4) { index ->
                ShimmerHomeItem()
                    .id("shimmer_home_item_$index")
                    .addTo(this)
            }
        } else if (state?.isLoading == false) {
            state.data.onEach { item ->
                ModelHomeItem(
                    item = item,
                    glide = glide,
                    onProductClick = onProductClick
                )
                    .id("home_item_${item.id}")
                    .addTo(this)
            }
        }
    }
}