package com.example.feature_home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature_home.data.local.model.CacheDataSource

@Dao
interface HomeDao {

    @Query("SELECT * FROM ${HomeDatabase.DATABASE_NAME}")
    suspend fun fetchCache(): List<CacheDataSource>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(cache: List<CacheDataSource>)

    @Query("DELETE FROM ${HomeDatabase.DATABASE_NAME}")
    suspend fun clearCache()
}