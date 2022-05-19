package com.example.nft.data

import androidx.lifecycle.LiveData

class FavRepository(private val favDao : FavoritesDao) {

    val readAllData : LiveData<List<FavoriteItem>> = favDao.readAllFav()

    suspend fun addFav(fav : FavoriteItem){
        favDao.addFav(fav)
    }
    suspend fun deleteFav(fav:FavoriteItem){
        favDao.deleteFav(fav)
    }
    suspend fun deleteAllFav(){
        favDao.deleteAllFav()
    }
}