package com.example.feature_search.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature_search.data.local.model.CacheDataSource

@Dao
interface SearchDao {

    @Query("SELECT * FROM ${SearchDatabase.DATABASE_NAME} WHERE modelFull LIKE '%' || :searchQuery || '%'")
    suspend fun fetchCache(searchQuery: String): List<CacheDataSource>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(cache: List<CacheDataSource>)

    @Query("DELETE FROM ${SearchDatabase.DATABASE_NAME}")
    suspend fun clearCache()
}