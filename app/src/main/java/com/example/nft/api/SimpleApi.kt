package com.example.nft.api

import com.example.nft.model.Balance
import com.example.nft.model.Item
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SimpleApi {

    @GET("allitems")
    suspend fun getItem() : Response<List<Item>>

    @GET("balance/{address}")
    suspend fun getBalance(@Path (value = "address") address : String) : Response<Balance>


    @GET("purshased/{address}")
    suspend fun getPurshased(@Path (value = "address") address : String) : Response<List<Item>>

    @GET("onsaleperartist/{address}")
    suspend fun getOnSale(@Path (value = "address") address : String) : Response<List<Item>>
}