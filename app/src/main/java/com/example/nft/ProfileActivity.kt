package com.example.nft

import android.app.ActivityOptions
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.SharedPreferences
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.nft.utils.ApiService
import com.example.nft.utils.CustomerService
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val preferences: SharedPreferences =
            getSharedPreferences("pref", Context.MODE_PRIVATE)

        val txtEmail = findViewById<TextView>(R.id.txtEmail)
        txtEmail.text = preferences.getString("email","")

        val tvwalletadress = findViewById<TextView>(R.id.tvwalletadress)
        tvwalletadress.text = preferences.getString("name","")

        val txtName = findViewById<TextView>(R.id.txtName)
        txtName.text = preferences.getString("wallet_address","")

        val txtUrl = findViewById<TextView>(R.id.txtUrl)
        txtUrl.text = preferences.getString("url","")

        val txtBio = findViewById<TextView>(R.id.txtBio)
        txtBio.text = preferences.getString("bio","")



        val imageprofile = findViewById<ImageView>(R.id.ImageProfile)
       Picasso.get()
            .load(preferences.getString("profile_picture", ""))
            .into(imageprofile)



        val btnupdate = findViewById(R.id.btnUpdate) as ImageView
        val btnPassword = findViewById(R.id.btnResetPaswword) as ImageView

        val btnCopy = findViewById(R.id.btncopy) as ImageView
        val btnlogout = findViewById(R.id.btnlogout) as ImageView

        btnupdate!!.setOnClickListener{
            val goToUpdatePage = Intent(this, UpdateProfile::class.java)
            startActivity(goToUpdatePage)
        }
        btnPassword!!.setOnClickListener{
            ApiService.customerService.recover( CustomerService.recoverBody(
                preferences.getString("email","").toString()
            )).enqueue(
                object : Callback<CustomerService.RecoverResponse>{
                    override fun onResponse(
                        call: Call<CustomerService.RecoverResponse>,
                        response: Response<CustomerService.RecoverResponse>
                    ) {
                        val preferences: SharedPreferences =
                            getSharedPreferences("pref", Context.MODE_PRIVATE)
                        val editor = preferences.edit()
                        editor.putString("TokenResetPassword", response.body()?.Token.toString())
                        Toast.makeText(this@ProfileActivity, "You have a change password Token", Toast.LENGTH_SHORT).show()
                        navigate()
                    }

                    override fun onFailure(
                        call: Call<CustomerService.RecoverResponse>,
                        t: Throwable
                    ) {
                        Log.d("FAIL", "fail")
                    }

                })
        }

        btnlogout!!.setOnClickListener{

                val dialogBuilder = AlertDialog.Builder(this)


                dialogBuilder.setMessage("Are you sure you want to logout ?")

                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton("Yes", DialogInterface.OnClickListener {
                            dialog, id ->
                        getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit().clear().apply()
                        val mainIntent = Intent(this@ProfileActivity, LoginActivity::class.java)
                        startActivity(mainIntent)
                        finish()
                    })
                    .setNegativeButton("No", DialogInterface.OnClickListener {
                            dialog, id -> dialog.cancel()
                    })

                val alert = dialogBuilder.create()
                alert.setTitle("Logout")
                alert.show()

        }


    }

    private fun navigate(){
        val goreset = Intent(this, change_passwordActivity::class.java)
        startActivity(goreset, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())

    }
/*
    private fun copyTextToClipboard() {
        val textToCopy = etTextToCopy.text
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", textToCopy)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_LONG).show()
    }*/

}