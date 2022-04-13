package com.example.nft.utils

import com.example.nft.models.Customer
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

import retrofit2.http.Body
import retrofit2.http.Headers


interface ApiInterface {
    @POST("/customers")
    fun registerUser(@Body info: Customer): retrofit2.Call<ResponseBody>

    companion object {

        var BASE_URL = "http://nftback.herokuapp.com"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}