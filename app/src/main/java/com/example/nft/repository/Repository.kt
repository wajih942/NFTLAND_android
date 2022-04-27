package com.example.nft.repository

import com.example.nft.api.RetrofitInstance
import com.example.nft.model.Item
import retrofit2.Response

class Repository {
    suspend fun getItem() : Response<List<Item>>{
        return RetrofitInstance.api.getItem()
    }
    suspend fun getPurshased() : Response<List<Item>>{
        return RetrofitInstance.api.getPurshased()
    }

    suspend fun getOnSale() : Response<List<Item>>{
        return RetrofitInstance.api.getOnSale()
    }
}