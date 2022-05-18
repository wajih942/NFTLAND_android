package com.example.nft

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.nft.models.Customer
import com.example.nft.utils.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateProfile : AppCompatActivity() {
    private val pickImage = 100
    private var imageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)

        val preferences: SharedPreferences =
            getSharedPreferences("pref", Context.MODE_PRIVATE)


        val txtEmail = findViewById<TextView>(R.id.txtNewEmail)
        txtEmail.text = preferences.getString("email","")

        val tvwalletadress = findViewById<TextView>(R.id.txtNewWalletAdress)
        tvwalletadress.text = preferences.getString("wallet_address","")

        val txtName = findViewById<TextView>(R.id.txtNewName)
        txtName.text = preferences.getString("name","")

        val txtUrl = findViewById<TextView>(R.id.txtNewUrl)
        txtUrl.text = preferences.getString("url","")

        val txtBio = findViewById<TextView>(R.id.txtNewBio)
        txtBio.text = preferences.getString("bio","")


        val btnBack = findViewById(R.id.btnBack) as ImageView

        btnBack!!.setOnClickListener{
            onBackPressed()
        }
        val btnsubmit =findViewById<Button>(R.id.BtnSubmit)

        val btnChooseImage = findViewById<Button>(R.id.changeImage)


        btnChooseImage.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }

        btnsubmit!!.setOnClickListener {



            val name = txtName.text.toString()
            val url = txtUrl.text.toString()
            val email = txtEmail.text.toString()
            val bio = txtBio.text.toString()
            val wallet_address = tvwalletadress.text.toString()


            val parcelFileDescriptor = contentResolver.openFileDescriptor(imageUri!!, "r", null) ?: return@setOnClickListener
            doupdate(name,url,bio, email, wallet_address)
            print(parcelFileDescriptor);
        }

        val imageprofile = findViewById<ImageView>(R.id.ImageProfile)
        val media = preferences.getString("profile_picture", "")
        if (media !== null) {
            Glide.with(this)
                .load(media)
                .into(imageprofile)
        } else {
            imageprofile.setImageResource(R.drawable.ic_launcher_background)
        }


    }

    private fun doupdate(name: String, url: String, bio: String, email: String, wallet_address: String) {
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
        if (profile_picture?.body != null) {

            println("++++++++++++++++++++++++++++++++++++" + profile_picture)
            val preferences: SharedPreferences =
                getSharedPreferences("pref", Context.MODE_PRIVATE)

            val id= preferences.getString("id","")
            apiInterface.customerService.updateProfile(data, profile_picture,id).enqueue(object :
                Callback<Customer> {
                override fun onResponse(
                    call: Call<Customer>,
                    response: Response<Customer>
                ) {
                    if (response.code()==201) {

                        Log.i("Response c bon", response.body().toString())
                        Toast.makeText(this@UpdateProfile, "Profile Update please refresh ", Toast.LENGTH_SHORT).show()
                        onBackPressed()

                    } else {
                        Log.i("onResponse hhhhaha", data.toString())

                        Log.i("OnResponse non", response.body().toString())
                    }
                }
                override fun onFailure(call: Call<Customer>, t: Throwable) {

                    Toast.makeText(this@UpdateProfile, "Connexion error!", Toast.LENGTH_SHORT).show()
                }

            })
        }
        else{
            Toast.makeText(this@UpdateProfile, "Choose Image!", Toast.LENGTH_SHORT).show()

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            val ImageProfile = findViewById<ImageView>(R.id.ImageProfile)
            ImageProfile?.setImageURI(imageUri)
        }
    }
}