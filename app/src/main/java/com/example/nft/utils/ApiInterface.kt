package com.example.nft.utils

import com.example.nft.models.Customer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.Call

interface ApiInterface {
    @POST("register")
    fun register(@Query("name") name: String,
                 @Query("url") url: String,
                 @Query("bio") bio: String,
                 @Query("email") email: String,
                 @Query("password") password: String,
                 @Query("wallet_address") wallet_address: String): Call<Customer>

    companion object {

        var BASE_URL = "https://nftback.herokuapp.com/customers"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}