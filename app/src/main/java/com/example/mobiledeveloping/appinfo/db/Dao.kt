package com.example.mobiledeveloping.appinfo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.utils.ListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ListItem)
    @Delete
    suspend fun insertItem(item: ListItem)
    @Query("SELECT * FROM main WHERE category LIKE :cat")
    fun getAllItemsByCategory(cat: String): Flow<List <ListItem>>
    @Query("SELECT * FROM main WHERE isFav = 1")
    fun getFavorites(): Flow<List<ListItem>>
}