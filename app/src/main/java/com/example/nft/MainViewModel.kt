package com.example.nft

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nft.model.Balance
import com.example.nft.model.Item
import com.example.nft.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {
    val myResponse: MutableLiveData<Response<List<Item>>> = MutableLiveData()
    val myResponse1: MutableLiveData<Response<Balance>> = MutableLiveData()
    fun getItem(){
        viewModelScope.launch {
            val response = repository.getItem()
            myResponse.value = response
        }

    }
    fun getBalance(address : String){
        viewModelScope.launch {
            val response = repository.getBalance(address)
            myResponse1.value = response
        }

    }
    fun getPurshased(address : String){
        viewModelScope.launch {
            val response = repository.getPurshased(address)
            myResponse.value = response
        }

    }

    fun getOnSale(address : String){
        viewModelScope.launch {
            val response = repository.getOnSale(address)
            myResponse.value = response
        }

    }
}