package com.example.nft.api

import com.example.nft.model.Item
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {

    @GET("allitems")
    suspend fun getItem() : Response<List<Item>>


    @GET("purshased/0x0D0f59580D0c88B784ee02Dc178e20481CBd8c5b")
    suspend fun getPurshased() : Response<List<Item>>

    @GET("onsaleperartist/0x0D0f59580D0c88B784ee02Dc178e20481CBd8c5b")
    suspend fun getOnSale() : Response<List<Item>>
}