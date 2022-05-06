package com.example.nft

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.nft.utils.ApiService
import com.example.nft.utils.CustomerService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {





        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        val btnBack = findViewById(R.id.btnBack) as ImageView

        btnBack!!.setOnClickListener{
            onBackPressed()
        }
        val txtEmail = findViewById<EditText>(R.id.txtNewEmail)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val btncontinue = findViewById<Button>(R.id.btncontinue)
        val errorlayout = findViewById<LinearLayout>(R.id.errorlayout)
        btncontinue.setOnClickListener{
            progressBar.visibility = View.VISIBLE
            ApiService.customerService.recover( CustomerService.recoverBody(
                txtEmail!!.text.toString(),
            )).enqueue(
                object : Callback<CustomerService.RecoverResponse> {
                    override fun onResponse(
                        call: Call<CustomerService.RecoverResponse>,
                        response: Response<CustomerService.RecoverResponse>
                    )
                    {
                        if (response.code() == 200 ) {

                            val preferences: SharedPreferences =
                            getSharedPreferences("pref", Context.MODE_PRIVATE)
                        val editor = preferences.edit()
                        editor.putString("TokenResetPassword", response.body()?.Token.toString())
                        Toast.makeText(this@ForgotPasswordActivity, "Check your Email", Toast.LENGTH_SHORT).show()
                            navigate()
                    }
                        else if (response.code() == 401){
                            progressBar.visibility = View.GONE
                            errorlayout.visibility=View.VISIBLE
                            Toast.makeText(this@ForgotPasswordActivity, "Try again", Toast.LENGTH_SHORT).show()


                        }
                        else {
                            Toast.makeText(this@ForgotPasswordActivity, "ERROR", Toast.LENGTH_SHORT).show()

                        }
                    }

                    override fun onFailure(
                        call: Call<CustomerService.RecoverResponse>,
                        t: Throwable
                    ) {
                        Log.d("FAIL", "fail")
                    }

                })
        }
    }
    private fun navigate(){
        val goreset = Intent(this, ResetPasswordActivity::class.java)
        startActivity(goreset, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())

    }

}