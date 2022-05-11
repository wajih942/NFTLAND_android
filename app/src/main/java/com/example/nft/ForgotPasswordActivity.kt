package com.example.nft

import android.app.ActivityOptions
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
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

                            //stocker token
                            val preferences: SharedPreferences =
                            getSharedPreferences("changepassword", Context.MODE_PRIVATE)
                        val editor = preferences.edit()
                        editor.putString("TokenResetPassword", response.body()?.Token.toString())
                            editor.putString("EmailResetPassword",  txtEmail!!.text.toString())



                            //alert dialog
                            val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(this@ForgotPasswordActivity)

                            dialogBuilder.setMessage("You have received a verification email")

                                .setCancelable(false)
                                // positive button text and action
                                .setPositiveButton("Yes", DialogInterface.OnClickListener {
                                        dialog, id ->
                                    val mainIntent = Intent(this@ForgotPasswordActivity, ResetPasswordActivity::class.java)
                                    startActivity(mainIntent)
                                    finish()
                                })
                                .setNegativeButton("No", DialogInterface.OnClickListener {
                                        dialog, id -> dialog.cancel()
                                })

                            val alert = dialogBuilder.create()
                            alert.setTitle("Password Reset")
                            alert.show()
                            Log.d("MyActivity", response.body()?.Token.toString())
                            Log.d("MyActivity", txtEmail!!.text.toString())
                    }
                        else if (response.code() == 401){
                            progressBar.visibility = View.GONE
                            errorlayout.visibility=View.VISIBLE
                            Toast.makeText(this@ForgotPasswordActivity, "Try again", Toast.LENGTH_SHORT).show()


                        }
                        else {
                            val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(this@ForgotPasswordActivity)
                            dialogBuilder.setMessage("Try again")

                            val alert = dialogBuilder.create()
                            alert.setTitle("Password Reset")
                            alert.show()
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