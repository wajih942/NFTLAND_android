package com.example.nft.utils
import retrofit2.Call
import com.example.nft.models.Customer
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface CustomerService {
    data class LoginResponse(
        @SerializedName("token")
        val token: String,
        val customer: Customer
    )


    data class CustomerResponse(
        @SerializedName("customer")
        val customer: Customer
    )
    data class LoginBody(val email: String, val password: String)

    data class CustomerBody(val email: String, val password: String, val name: String, val bio: String, val walletAdress: String, val url: String,val profile_picture:String)


    @POST("/customers/login")
    fun login(@Body loginBody: LoginBody): Call<LoginResponse>
/*
    @Multipart
    @POST("/customers")
    fun register(@Part customerBody: CustomerBody,
    @Part profile_picture : MultipartBody.Part
    ): Call<CustomerResponse>

*/

    @Multipart
    @POST("customers")
    //fun register(@Body user:User,@Part): Call<User>
    fun register(@PartMap data : LinkedHashMap<String, RequestBody>,
                 @Part profile_picture: MultipartBody.Part

    ): Call<Customer>


}