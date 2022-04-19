package com.example.nft


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.nft.utils.ApiService
import com.example.nft.utils.CustomerService
import com.example.nft.utils.URIPathHelper
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

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

    var ImageProfile: ImageView? = null
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
            ApiService.customerService.register(
                CustomerService.CustomerBody(
                    txtEmail!!.text.toString(),
                    txtPassword!!.text.toString(),
                    txtName!!.text.toString(),
                    txtBio!!.text.toString(),
                    txtWalletAdd!!.text.toString(),
                    txtUrl!!.text.toString()
                )
            )
                .enqueue(
                    object : Callback<CustomerService.CustomerResponse> {
                        override fun onResponse(
                            call: Call<CustomerService.CustomerResponse>,
                            response: Response<CustomerService.CustomerResponse>
                        ) {
                            if (response.code() == 201) {

                                Toast.makeText(this@RegisterActivity, "Success", Toast.LENGTH_SHORT)
                                    .show()
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

         // Either Uri, File or String file path

    }

    private fun uploadImage() {
        val uriPathHelper = URIPathHelper()
        val filePath = uriPathHelper.getPath(this, imageUri!!)
        Log.i("FilePath", filePath.toString())
        val file = File(filePath)
        val requestFile: RequestBody =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
        val multiPartBody = MultipartBody.Part.createFormData("file", file.name, requestFile)    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            val ImageProfile = findViewById<ImageView>(R.id.ivProfile)
            ImageProfile?.setImageURI(imageUri)
            uploadImage()
        }
    }

}