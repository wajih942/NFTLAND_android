package com.example.nft.repository

import com.example.nft.api.RetrofitInstance
import com.example.nft.model.Balance
import com.example.nft.model.Item
import com.example.nft.model.ItemUpload
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.File

class Repository {
    suspend fun getItem() : Response<List<Item>>{
        return RetrofitInstance.api.getItem()
    }
    suspend fun getBalance(address : String) : Response<Balance>{
        return RetrofitInstance.api.getBalance(address)
    }
    suspend fun getPurshased(address : String) : Response<List<Item>>{
        return RetrofitInstance.api.getPurshased(address)
    }

    suspend fun getOnSale(address : String) : Response<List<Item>>{
        return RetrofitInstance.api.getOnSale(address)
    }
    suspend fun uploadItem(item : ItemUpload ,image : MultipartBody.Part){
        return RetrofitInstance.api.uploadItem(item, image)
    }
}