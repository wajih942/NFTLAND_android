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
        val Customer: Customer
    )

    data class RecoverResponse(
        @SerializedName("Token")
        val Token: String
    )

    data class ResetResponse(
        @SerializedName("message")
        val mssage: String
    )

    data class LoginBody(val email: String, val password: String)
    data class ResetBody(val email: String, val password: String)

    data class recoverBody(val email: String)

    @POST("/customers/reset/{token}")
    fun reset(@Body ResetBody: ResetBody,
        @Query("token") token: String?

    ): Call<ResetResponse>


    @POST("/customers/recover")
    fun recover(@Body changepasswordrecover: recoverBody): Call<RecoverResponse>


    @POST("/customers/login")
    fun login(@Body loginBody: LoginBody): Call<LoginResponse>


    @Multipart
    @POST("customers")
    fun register(@PartMap data : LinkedHashMap<String, RequestBody>,
                 @Part profile_picture: MultipartBody.Part

    ): Call<Customer>


}