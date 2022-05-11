package com.example.nft.api

import com.example.nft.model.Balance
import com.example.nft.model.Item
import com.example.nft.model.ItemUpload
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface SimpleApi {

    @GET("allitems")
    suspend fun getItem() : Response<List<Item>>

    @GET("balance/{address}")
    suspend fun getBalance(@Path (value = "address") address : String) : Response<Balance>


    @GET("purshased/{address}")
    suspend fun getPurshased(@Path (value = "address") address : String) : Response<List<Item>>

    @GET("onsaleperartist/{address}")
    suspend fun getOnSale(@Path (value = "address") address : String) : Response<List<Item>>

    @Multipart
    @POST("upload")
    suspend fun uploadItem(
        @Part itemUpload: ItemUpload,
        @Part image : MultipartBody.Part
    )
}