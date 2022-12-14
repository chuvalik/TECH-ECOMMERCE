package com.example.feature_profile.presentation.profile.model

import com.example.core_ui_models.UiEvent

sealed class ProfileEvent : UiEvent {
    object EditProfileButtonClicked : ProfileEvent()
    object ShoppingAddressButtonClicked : ProfileEvent()
    object OrderHistoryButtonClicked : ProfileEvent()
    object CardsButtonClicked : ProfileEvent()
    object NotificationsButtonClicked : ProfileEvent()
    object BackButtonClicked : ProfileEvent()
}