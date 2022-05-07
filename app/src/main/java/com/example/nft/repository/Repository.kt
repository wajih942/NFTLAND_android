package com.example.nft.repository

import com.example.nft.api.RetrofitInstance
import com.example.nft.model.Balance
import com.example.nft.model.Item
import retrofit2.Response

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
}