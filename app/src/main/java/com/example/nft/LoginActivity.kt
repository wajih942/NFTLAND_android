package com.example.nft

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.example.nft.utils.ApiService
import com.example.nft.utils.CustomerService
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


const val PREF_NAME = "LOGIN_PREF_NFT"
const val LOGIN = "EMAIL"
const val PASSWORD = "PASSWORD"
const val IS_REMEMBRED = "IS_REMEMBRED"

class LoginActivity : AppCompatActivity() {

    lateinit var txtEmail: TextInputEditText
    lateinit var txtPassword: TextInputEditText
    lateinit var cbRememberMe: CheckBox

    lateinit var mSharedPref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        txtEmail = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtPassword)
        cbRememberMe = findViewById(R.id.cbRememberMe)
        val btnLogin =findViewById<Button>(R.id.btnLogin)
        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        if (mSharedPref.getBoolean(IS_REMEMBRED, false)){
            navigate()
        }

        btnLogin!!.setOnClickListener{
            ApiService.customerService.login(
                CustomerService.LoginBody(
                    txtEmail!!.text.toString(),
                    txtPassword!!.text.toString(),

                )
            )
                .enqueue(
                    object : Callback<CustomerService.CustomerResponse> {
                        override fun onResponse(
                            call: Call<CustomerService.CustomerResponse>,
                            response: Response<CustomerService.CustomerResponse>
                        ) {
                            if (response.code() == 200) {
                                Toast.makeText(this@LoginActivity, "Success", Toast.LENGTH_SHORT).show()
                                navigate()
                                SaveUser()
                            } else {
                                Log.d("HTTP ERROR", "status code is " + response.code())
                            }
                        }

                        override fun onFailure(
                            call: Call<CustomerService.CustomerResponse>,
                            t: Throwable
                        ) {
                            Log.d("FAIL", "fail")
                        }
                    }
                )

        }

    }

    private fun navigate(){
        val mainIntent = Intent(this, ProfileActivity::class.java)
        startActivity(mainIntent)
    }
    private fun SaveUser(){
        if (cbRememberMe.isChecked){
            mSharedPref.edit().apply{
                putBoolean(IS_REMEMBRED, true)
                putString(LOGIN, txtEmail.text.toString())
                putString(PASSWORD, txtPassword.text.toString())
            }.apply()

        }else{
            mSharedPref.edit().clear().apply()
        }

    }
}