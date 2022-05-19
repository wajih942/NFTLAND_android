package com.example.nft.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoritesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFav(fav : FavoriteItem)

    @Query("SELECT * FROM favorites_table ORDER BY id")
    fun readAllFav():LiveData<List<FavoriteItem>>

    @Query("DELETE FROM favorites_table")
    suspend fun deleteAllFav()

    @Delete
    suspend fun deleteFav(fav:FavoriteItem)
}