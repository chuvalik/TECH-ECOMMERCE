package com.example.feature_profile.presentation.model

import com.example.core.ui.UiState
import com.example.feature_profile.domain.model.DomainDataSource

data class ProfileState(
    val data: DomainDataSource = DomainDataSource(
        id = "",
        email = "",
        image = "",
        firstName = "Unknown",
        lastName = "User",
        shippingAddresses = emptyList(),
        phoneNumber = ""
    ),
    val isLoading: Boolean = false,
) : UiState