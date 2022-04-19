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

        val bntRegister = findViewById<Button>(R.id.UiRegister)
        bntRegister!!.setOnClickListener{
            val mainIntent = Intent(this, RegisterActivity::class.java)
            startActivity(mainIntent)

        }
        if (mSharedPref.getBoolean(IS_REMEMBRED, false)){
            SaveUser()
        }

        btnLogin!!.setOnClickListener{
            ApiService.customerService.login(
                CustomerService.LoginBody(
                    txtEmail!!.text.toString(),
                    txtPassword!!.text.toString(),
                )
            )
                .enqueue(
                    object : Callback<CustomerService.LoginResponse> {
                        override fun onResponse(
                            call: Call<CustomerService.LoginResponse>,
                            response: Response<CustomerService.LoginResponse>
                        ) {
                            if (response.code() == 200) {
                                Toast.makeText(this@LoginActivity, "Success", Toast.LENGTH_SHORT).show()
                                navigate()

                            }
                            else if (response.code() == 401){
                                Toast.makeText(this@LoginActivity, "Check you credentials ", Toast.LENGTH_SHORT).show()
                            }
                            else {
                                Toast.makeText(this@LoginActivity, "ERROR", android.widget.Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(
                            call: Call<CustomerService.LoginResponse>,
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
        finish()
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