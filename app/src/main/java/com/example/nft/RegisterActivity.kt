package com.example.nft

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import com.example.nft.models.Customer
import com.example.nft.utils.ApiInterface
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {

    lateinit var txtPassword: TextInputEditText
    lateinit var txtLayoutPassword: TextInputLayout

    lateinit var txtUrl: TextInputEditText
    lateinit var txtLayoutUrl: TextInputLayout

    lateinit var txtName: TextInputEditText
    lateinit var txtLayoutName: TextInputLayout

    lateinit var txtBio: TextInputEditText
    lateinit var txtLayoutBio: TextInputLayout

    lateinit var txtEmail: TextInputEditText
    lateinit var txtLayoutEmail: TextInputLayout

    lateinit var txtWalletAdd: TextInputEditText
    lateinit var txtLayoutWalletAdd: TextInputLayout

    lateinit var btnRegister: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        txtName = findViewById(R.id.txtName)
        txtLayoutName = findViewById(R.id.txtLayoutName)

        txtPassword = findViewById(R.id.txtPassword)
        txtLayoutPassword = findViewById(R.id.txtLayoutPassword)

        txtUrl = findViewById(R.id.txtUrl)
        txtLayoutUrl = findViewById(R.id.txtLayoutUrl)

        txtBio = findViewById(R.id.txtBio)
        txtLayoutBio = findViewById(R.id.txtLayoutBio)

        txtEmail = findViewById(R.id.txtEmail)
        txtLayoutEmail = findViewById(R.id.txtLayoutEmail)

        txtWalletAdd = findViewById(R.id.txtWalletAdd)
        txtLayoutWalletAdd = findViewById(R.id.txtLayoutWalletAdd)

        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener{
          //  doLogin()
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            }


        }





    private fun doLogin(){
        if (validate()){
            val apiInterface = ApiInterface.create()
            //progBar.visibility = View.VISIBLE

            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )

            apiInterface.register(txtName.text.toString(),txtUrl.toString(),txtBio.toString(),txtEmail.toString(), txtPassword.text.toString(),txtWalletAdd.toString()).enqueue(object : Callback<Customer> {

                override fun onResponse(call: Call<Customer>, response: Response<Customer>) {

                    val customer = response.body()

                    if (customer != null){
                        Toast.makeText(this@RegisterActivity, "Register Success", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@RegisterActivity, "Register failed", Toast.LENGTH_SHORT).show()
                    }

                   // progBar.visibility = View.INVISIBLE
                    window.clearFlags( WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                }

                override fun onFailure(call: Call<Customer>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "Connexion error!", Toast.LENGTH_SHORT).show()

                   // progBar.visibility = View.INVISIBLE
                    window.clearFlags( WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                }

            })

        }
    }
    private fun validate(): Boolean {


        return true
    }
}