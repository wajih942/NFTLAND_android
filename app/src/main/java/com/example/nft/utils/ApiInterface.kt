package com.example.nft.utils


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object ApiService {

    //private const val BASE_URL = "https://nftback.herokuapp.com/"
    private const val BASE_URL = "http://192.168.1.2:3001/"


    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }


    val customerService: CustomerService by lazy {
        retrofit().create(CustomerService::class.java)
    }


}