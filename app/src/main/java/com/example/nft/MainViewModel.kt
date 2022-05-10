package com.example.nft

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nft.model.*
import com.example.nft.repository.Repository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Part
import retrofit2.http.PartMap

class MainViewModel(private val repository: Repository) : ViewModel() {
    val myResponse: MutableLiveData<Response<List<Item>>> = MutableLiveData()
    val myResponse1: MutableLiveData<Response<Balance>> = MutableLiveData()
    val myResponse2: MutableLiveData<Response<TrInfo>> = MutableLiveData()


    fun buynft(itemInfo : ItemInfo){
        viewModelScope.launch {
            val response = repository.buynft(itemInfo)
            myResponse2.value = response
        }
    }
    fun uploadItem(data : LinkedHashMap<String, RequestBody>, image : MultipartBody.Part){
        viewModelScope.launch {
            val response= repository.uploadItem(data,image)
            myResponse2.value = response
        }
    }
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