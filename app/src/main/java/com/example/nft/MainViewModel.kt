package com.example.nft

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nft.model.Item
import com.example.nft.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {
    val myResponse: MutableLiveData<Response<List<Item>>> = MutableLiveData()
    fun getItem(){
        viewModelScope.launch {
            val response = repository.getItem()
            myResponse.value = response
        }

    }
    fun getPurshased(){
        viewModelScope.launch {
            val response = repository.getPurshased()
            myResponse.value = response
        }

    }

    fun getOnSale(){
        viewModelScope.launch {
            val response = repository.getOnSale()
            myResponse.value = response
        }

    }
}