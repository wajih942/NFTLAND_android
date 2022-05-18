package com.example.nft

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.nft.utils.ApiService
import com.example.nft.utils.CustomerService
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        val updatepasswordBtn = findViewById<Button>(R.id.updatepasswordBtn)
        val txtNewPass = findViewById<EditText>(R.id.txtNewPass)




        btnBack!!.setOnClickListener{
            onBackPressed()
        }




        updatepasswordBtn!!.setOnClickListener{

            val preferences: SharedPreferences =
                getSharedPreferences("changepassword", Context.MODE_PRIVATE)
            val token= preferences.getString("TokenResetPassword","")
            val email= preferences.getString("EmailResetPassword","")
            println("--------------------------------------------------------------")
            println(token.toString())
            println(email.toString())
            ApiService.customerService.reset(
                CustomerService.ResetBody(
                    email!!,
                    txtNewPass!!.text.toString()
                ),token
            )
                .enqueue(
                    object : Callback<CustomerService.ResetResponse> {
                        override fun onResponse(
                            call: Call<CustomerService.ResetResponse>,
                            response: Response<CustomerService.ResetResponse>
                        ) {

                            if (response.code() == 200)
                            {
                                Toast.makeText(this@ResetActivity, "Password changed successfully", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                Toast.makeText(this@ResetActivity, "ERROR", Toast.LENGTH_SHORT).show()

                            }
                        }

                        override fun onFailure(
                            call: Call<CustomerService.ResetResponse>,
                            t: Throwable
                        ) {
                            Toast.makeText(this@ResetActivity, "ERROR", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
        }


    }
}