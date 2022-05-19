package com.example.nft.data


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavViewModel(application:Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<FavoriteItem>>
    private  val repository : FavRepository
    init {
        val favDao = FavoriteDatabase.getDatabase(application).favoriteDao()
        repository = FavRepository(favDao)
        readAllData = repository.readAllData
    }
    fun addFav(fav:FavoriteItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFav(fav)
        }

    }
    fun deleteFav(fav: FavoriteItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFav(fav)
        }
    }
    fun deleteAllFav(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllFav()
        }
    }
}