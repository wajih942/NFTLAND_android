package com.example.nft.api

import com.example.nft.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

    @POST("buynft")
    suspend fun buynft(
        @Body itemInfo : ItemInfo
    ): Response<TrInfo>

    @POST("marketsale")
    suspend fun createsale(
        @Body marketSale : MarketSale
    ): Response<TrInfo>

    @Multipart
    @POST("upload")
    fun uploadItem(@PartMap data : LinkedHashMap<String, RequestBody>,
                 @Part image: MultipartBody.Part

    ): Response<TrInfo>
}