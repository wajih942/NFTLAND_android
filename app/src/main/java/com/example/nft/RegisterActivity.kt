package com.example.nft


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.service.controls.ControlsProviderService
import android.util.Log

import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.nft.models.Customer
import com.example.nft.utils.ApiService
import com.example.nft.utils.CustomerService

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
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

    private val pickImage = 100
    private var imageUri: Uri? = null

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

        txtWalletAdd = findViewById(R.id.txtWalletAdress)
        txtLayoutWalletAdd = findViewById(R.id.txtLayoutWalletAdress)

        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val btnChooseImage = findViewById<Button>(R.id.btnChooseImage)



        btnChooseImage.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }



        btnRegister!!.setOnClickListener {



            val name = txtName.text.toString()
            val url = txtUrl.text.toString()
            val email = txtEmail.text.toString()
            val bio = txtBio.text.toString()
            val password = txtPassword.text.toString()
            val wallet_address = txtWalletAdd.text.toString()

            val parcelFileDescriptor = contentResolver.openFileDescriptor(imageUri!!, "r", null) ?: return@setOnClickListener
            doRegister(name,url,bio, email, password, wallet_address)
            print(parcelFileDescriptor);
        }

    }

    private fun doRegister(name: String, url: String, email: String, bio: String,password : String,wallet_address:String){

            if (imageUri == null) {
                println("image null")
            }

            val stream = contentResolver.openInputStream(imageUri!!)
            println("-------------------------------------" + stream)
            val request =
                stream?.let {
                    RequestBody.create(
                        "image/jpg".toMediaTypeOrNull(),
                        it.readBytes()
                    )
                } // read all bytes using kotlin extension
            val profile_picture = request?.let {
                MultipartBody.Part.createFormData(
                    "profile_picture",
                    "profile_picture.jpg",
                    it
                )
            }


            Log.d("MyActivity", "upload file")

            val apiInterface = ApiService
            val data: LinkedHashMap<String, RequestBody> = LinkedHashMap()

            data["name"] = name.toRequestBody(MultipartBody.FORM)
            data["url"] = url.toRequestBody(MultipartBody.FORM)
            data["email"] = email.toRequestBody(MultipartBody.FORM)
            data["wallet_address"] = wallet_address.toRequestBody(MultipartBody.FORM)
            data["bio"] = bio.toRequestBody(MultipartBody.FORM)
            data["password"] = password.toRequestBody(MultipartBody.FORM)
            if (profile_picture?.body != null) {

                println("++++++++++++++++++++++++++++++++++++" + profile_picture)
                apiInterface.customerService.register(data, profile_picture).enqueue(object :
                    Callback<Customer> {
                    override fun onResponse(
                        call: Call<Customer>,
                        response: Response<Customer>
                    ) {
                        if (response.code()==201) {

                            Log.i("Response c bon", response.body().toString())
                            Toast.makeText(this@RegisterActivity, "Thank you for joining us ", Toast.LENGTH_SHORT).show()
                            navigate()
                        } else {
                            Log.i("onResponse heyyyyy", data.toString())

                            Log.i("OnResponse non", response.body().toString())
                        }
                    }
                    override fun onFailure(call: Call<Customer>, t: Throwable) {

                        Toast.makeText(this@RegisterActivity, "Connexion error!", Toast.LENGTH_SHORT).show()
                    }

                })
            }
            else{
                Toast.makeText(this@RegisterActivity, "Choose Image!", Toast.LENGTH_SHORT).show()

            }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            val ImageProfile = findViewById<ImageView>(R.id.ivProfile)
            ImageProfile?.setImageURI(imageUri)
        }
    }

    private fun navigate(){
        val mainIntent = Intent(this, ProfileActivity::class.java)
        startActivity(mainIntent)
        finish()
    }
}