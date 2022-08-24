package com.example.feature_cart.domain.repository

import com.example.core.utils.Resource
import com.example.feature_cart.domain.model.DomainDataSource
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun fetchData(userId: String): Flow<Resource<List<DomainDataSource>>>

    suspend fun insertData(userId: String, item: DomainDataSource)
}