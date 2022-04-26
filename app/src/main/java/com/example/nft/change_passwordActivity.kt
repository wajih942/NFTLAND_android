package com.example.nft

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.nft.utils.ApiService
import com.example.nft.utils.CustomerService
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class change_passwordActivity : AppCompatActivity() {


    lateinit var txtEmail: TextInputEditText
    lateinit var txtPassword: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)



        txtEmail = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtNewPass)


        val preferences: SharedPreferences =
            getSharedPreferences("pref", Context.MODE_PRIVATE)



        val btnPassword = findViewById(R.id.btnResetPaswword) as ImageView

        btnPassword!!.setOnClickListener{
            ApiService.customerService.reset(
                CustomerService.ResetBody(
                    txtEmail!!.text.toString(),
                    txtPassword!!.text.toString(),

                ),preferences.getString("TokenResetPassword","")

            )

                .enqueue(
                object : Callback<CustomerService.ResetResponse>{
                    override fun onResponse(
                        call: Call<CustomerService.ResetResponse>,
                        response: Response<CustomerService.ResetResponse>
                    ) {
                        if (response.code() == 200)
                        {
                            Toast.makeText(this@change_passwordActivity, "Password changed successfully", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Toast.makeText(this@change_passwordActivity, "ERROR", Toast.LENGTH_SHORT).show()

                        }
                    }

                    override fun onFailure(
                        call: Call<CustomerService.ResetResponse>,
                        t: Throwable
                    ) {
                        Toast.makeText(this@change_passwordActivity, "ERROR", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }



    }
}