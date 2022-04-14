package com.example.nft.utils
import retrofit2.Call
import com.example.nft.models.Customer
import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.POST

interface CustomerService {
    data class CustomerResponse(
        @SerializedName("customer")
        val customer: Customer
    )
    data class LoginBody(val email: String, val password: String)

    data class CustomerBody(val email: String, val password: String, val name: String, val bio: String, val walletAdress: String, val url: String)


    @POST("/customers/login")
    fun login(@Body loginBody: LoginBody): Call<CustomerResponse>

    @POST("/customers")
    fun register(@Body userBody: CustomerBody): Call<CustomerResponse>
}