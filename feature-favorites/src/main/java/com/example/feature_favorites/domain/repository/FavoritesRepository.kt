package com.example.feature_favorites.domain.repository

import com.example.core.ui.UiText
import com.example.core.utils.Resource
import com.example.feature_favorites.domain.model.DomainDataSource
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    fun fetchData(userId: String): Flow<Resource<List<DomainDataSource>>>

    fun insertData(userId: String, data: DomainDataSource): Flow<Resource<UiText>>

    fun deleteData(userId: String, data: DomainDataSource): Flow<Resource<UiText>>

    fun isDataExist(userId: String, id: String): Flow<Resource<Boolean>>
}