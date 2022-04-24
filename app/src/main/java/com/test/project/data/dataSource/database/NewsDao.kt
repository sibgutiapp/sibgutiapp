package com.test.project.data.dataSource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.project.data.remote.entity.ApiNewsDatabase

@Dao
interface NewsDao {
    @Insert
    suspend fun insertAll(list: List<ApiNewsDatabase>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(apiNewsDatabase: ApiNewsDatabase)

    @Query("DELETE FROM news_hash")
    suspend fun deleteAll()

    @Query("SELECT * FROM news_hash")
    suspend fun getAll(): List<ApiNewsDatabase>

    @Query("SELECT COUNT(*) FROM news_hash")
    suspend fun size(): Int
}