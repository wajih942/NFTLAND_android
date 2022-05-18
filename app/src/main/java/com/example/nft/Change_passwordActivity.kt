package com.example.nft


import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.nft.utils.ApiService
import com.example.nft.utils.CustomerService
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Change_passwordActivity : AppCompatActivity() {

    lateinit var txtPassword: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)


        val btnPassword = findViewById<Button>(R.id.updatepasswordBtn)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        btnBack!!.setOnClickListener{
            onBackPressed()
        }


        btnPassword!!.setOnClickListener{
            val preferences: SharedPreferences =
                getSharedPreferences("pref", Context.MODE_PRIVATE)
            val token= preferences.getString("TokenResetPassword","")
            val email=preferences.getString("email","")
            println("-------------------------------------" + email)
            if (validate()){
                ApiService.customerService.reset(
                CustomerService.ResetBody(
                    email!!,
                    txtPassword!!.text.toString(),

                    ),token
            )
                .enqueue(
                    object : Callback<CustomerService.ResetResponse>{
                        override fun onResponse(
                            call: Call<CustomerService.ResetResponse>,
                            response: Response<CustomerService.ResetResponse>
                        ) {

                            if (response.code() == 200)
                            {
                                Toast.makeText(this@Change_passwordActivity, "Password changed successfully", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                Toast.makeText(this@Change_passwordActivity, "ERROR", Toast.LENGTH_SHORT).show()

                            }
                        }

                        override fun onFailure(
                            call: Call<CustomerService.ResetResponse>,
                            t: Throwable
                        ) {
                            Toast.makeText(this@Change_passwordActivity, "ERROR", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
        }



    }}
    private fun validate(): Boolean {
        txtPassword.error = null


        if (txtPassword.text!!.isEmpty()){
            txtPassword.error = getString(R.string.mustNotBeEmpty)
            return false
        }

        return true
    }
}