package com.example.feature_profile.data.remote.model

data class CloudDataSource(
    val userId: String = "",
    val email: String = "",
    val image: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val shippingAddresses: List<CloudShippingAddress> = emptyList(),
    val phoneNumber: String = "",
)

data class CloudShippingAddress(
    val country: String = "",
    val city: String = "",
    val street1: String = "",
    val street2: String = "",
    val state: String = "",
    val zip: String = "",
    val phone: String = "",
)