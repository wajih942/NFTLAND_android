package com.example.nft

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.nft.utils.ApiService
import com.example.nft.utils.CustomerService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        val btnBack = findViewById(R.id.btnBack) as ImageView

        btnBack!!.setOnClickListener{
            onBackPressed()
        }
        val btnconfirm = findViewById<Button>(R.id.btnconfirm)



        btnconfirm!!.setOnClickListener{

            val preferences: SharedPreferences =
                getSharedPreferences("changepassword", Context.MODE_PRIVATE)
            val token= preferences.getString("TokenResetPassword","")
            val email= preferences.getString("EmailResetPassword","")
            println("----------------------------hahahah----------------------------------")
            println(token.toString())
            println(email.toString())

            val mainIntent = Intent(this, ResetActivity::class.java)

            startActivity(mainIntent)
            finish()
        }

    }
}