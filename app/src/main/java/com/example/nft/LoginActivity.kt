package com.example.nft

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.*
import com.example.nft.utils.ApiService
import com.example.nft.utils.CustomerService
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.LinearLayout





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
        val waiting = findViewById<TextView>(R.id.textWaiting)
        val btnLogin =findViewById<Button>(R.id.btnLogin)
        val waitingLayout = findViewById<LinearLayout>(R.id.waiting)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val LoginFailed = findViewById<ImageView>(R.id.LoginFailed)
        mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        val forgotpassord = findViewById<View>(R.id.forgetpassword) as LinearLayout

        forgotpassord.setOnClickListener {
            val mainIntent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(mainIntent)
        }

        waitingLayout.visibility = View.GONE



        val bntRegister = findViewById<Button>(R.id.UiRegister)
        bntRegister!!.setOnClickListener{
            val mainIntent = Intent(this, RegisterActivity::class.java)
            startActivity(mainIntent)

        }
        if (mSharedPref.getBoolean(IS_REMEMBRED, false)){
            navigate()
        }

        btnLogin!!.setOnClickListener{
            btnLogin.visibility = View.GONE
            waitingLayout.visibility = View.VISIBLE
            forgotpassord.visibility = View.GONE
        if (validate()){
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
                            if (response.code() == 200 ) {

                                val preferences: SharedPreferences =
                                    getSharedPreferences("pref", Context.MODE_PRIVATE)
                                val editor = preferences.edit()
                                editor.putString("token", response.body()?.token.toString())

                                editor.putString("id", response.body()?.Customer?._id.toString())
                                    .apply()
                                editor.putString("email", response.body()?.Customer?.email.toString())
                                    .apply()
                                editor.putString("wallet_address", response.body()?.Customer?.wallet_address.toString())
                                    .apply()
                                editor.putString("name", response.body()?.Customer?.name.toString())
                                    .apply()
                                editor.putString("url", response.body()?.Customer?.url.toString())
                                    .apply()
                                editor.putString("bio", response.body()?.Customer?.bio.toString())
                                    .apply()
                                editor.putString("profile_picture", response.body()?.Customer?.profile_picture.toString())
                                    .apply()
                                Toast.makeText(this@LoginActivity, "Success", Toast.LENGTH_SHORT).show()
                                SaveUser()
                                navigate()

                            }
                            else if (response.code() == 403){

                                progressBar.visibility = View.GONE
                                waiting.text = "Login Failed"
                                LoginFailed.visibility = View.VISIBLE

                                waitingLayout.isClickable = true

                                waitingLayout.setOnClickListener {
                                    waitingLayout.visibility = View.GONE
                                    forgotpassord.visibility = View.VISIBLE
                                    LoginFailed.visibility = View.VISIBLE

                                    txtEmail.setText("")
                                    txtPassword.setText("")
                                    btnLogin.visibility = View.VISIBLE

                                }                            }


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
    }




    private fun validate(): Boolean {
        txtEmail.error = null
        txtPassword.error = null

        if (txtEmail.text!!.isEmpty()){
            txtEmail.error = getString(R.string.mustNotBeEmpty)
            return false
        }

        if (txtPassword.text!!.isEmpty()){
            txtPassword.error = getString(R.string.mustNotBeEmpty)
            return false
        }

        return true
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