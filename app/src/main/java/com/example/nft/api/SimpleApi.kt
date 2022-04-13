package com.example.nft.api

import com.example.nft.model.Item
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {

    @GET("allitems")
    suspend fun getItem() : Response<List<Item>>
}