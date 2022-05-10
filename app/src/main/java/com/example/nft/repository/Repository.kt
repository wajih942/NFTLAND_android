package com.example.nft.repository

import com.example.nft.api.RetrofitInstance
import com.example.nft.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Part
import retrofit2.http.PartMap
import java.io.File

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
    suspend fun uploadItem(data : LinkedHashMap<String, RequestBody>, image : MultipartBody.Part) : Response<TrInfo>{
        return RetrofitInstance.api.uploadItem(data,image)
    }
    suspend fun buynft(itemInfo : ItemInfo): Response<TrInfo>{
        return RetrofitInstance.api.buynft(itemInfo)
    }
}