package com.example.nft

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.FileUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nft.model.ItemUpload
import com.example.nft.repository.Repository
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.nio.file.Path

class MintNftActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    lateinit var sharedPrefrences : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mint_nft)
        var cancelbtn = findViewById<Button>(R.id.cancelbtn)
        var buybtn = findViewById<Button>(R.id.buybtn)
        var name = intent.getStringExtra("title")
        var description = intent.getStringExtra("desc")
        val details = intent.getStringExtra("size")
        val price = intent.getStringExtra("price")
        var st = intent.getStringExtra("uri")
        val uri : Uri = Uri.parse(st)
        sharedPrefrences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        val address = sharedPrefrences.getString("address","").toString()
        val privateKey = sharedPrefrences.getString("private","").toString()
        val etgasprice = findViewById<EditText>(R.id.et_gasprice)
        val etgasLimit = findViewById<EditText>(R.id.et_gasLimit)

        /*val txhash = "wajih12345678"

        buybtn.setOnClickListener {
            val intent = Intent(this,MintCheckActivity::class.java)
            intent.putExtra("txhash",txhash.toString())
            intent.putExtra("price",price.toString())
            startActivity(intent)
            finish()
        }*/
        val stream = contentResolver.openInputStream(uri)
        val request =
            stream?.let {
                RequestBody.create(
                    "image/jpg".toMediaTypeOrNull(),
                    it.readBytes()
                )
            } // read all bytes using kotlin extension
        val image = request?.let {
            MultipartBody.Part.createFormData(
                "image",
                "image.jpg",
                it
            )
        }

        val data: LinkedHashMap<String, RequestBody> = LinkedHashMap()

        if (name != null) {
            data["name"] = name.toRequestBody(MultipartBody.FORM)
        }
        if (description != null) {
            data["description"] = description.toRequestBody(MultipartBody.FORM)
        }
        if (details != null) {
            data["details"] = details.toRequestBody(MultipartBody.FORM)
        }
        if (price != null) {
            data["price"] = price.toRequestBody(MultipartBody.FORM)
        }
        data["address"] = address.toRequestBody(MultipartBody.FORM)
        data["gaslimit"] = etgasLimit.text.toString().toRequestBody(MultipartBody.FORM)
        data["gasprice"] = etgasprice.text.toString().toRequestBody(MultipartBody.FORM)





        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)




        buybtn.setOnClickListener {
            if (image != null) {
                viewModel.uploadItem(data,image)
            }
        }
        Log.i("rrrrrrrrr",name
        + description + price + details + privateKey + address)
        viewModel.myResponse2.observe(this, Observer { response ->
            if (response.isSuccessful){
                var intent = Intent(this,MintCheckActivity::class.java)
                intent.putExtra("txhash",response.body()?.txHash.toString())
                startActivity(intent)
            }else{
                Log.i("Response","error")

            }

        })
    }
}