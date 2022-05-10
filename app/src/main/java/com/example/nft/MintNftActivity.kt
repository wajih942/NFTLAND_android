package com.example.nft

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.FileUtils
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.nft.model.ItemUpload
import com.example.nft.repository.Repository
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.nio.file.Path

class MintNftActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mint_nft)
        var cancelbtn = findViewById<Button>(R.id.cancelbtn)
        var buybtn = findViewById<Button>(R.id.buybtn)
        var title = intent.getStringExtra("title")
        val price = intent.getStringExtra("price")
        var st = intent.getStringExtra("uri")
        val uri : Uri = Uri.parse(st)

        val txhash = "wajih12345678"

        buybtn.setOnClickListener {
            val intent = Intent(this,MintCheckActivity::class.java)
            intent.putExtra("txhash",txhash.toString())
            intent.putExtra("price",price.toString())
            startActivity(intent)
            finish()
        }

        val stream = contentResolver.openInputStream(uri)
        /*val request =
            stream?.let {
                RequestBody.create(
                    "image/jpg",
                    it.readBytes()
                )
            } */
        /*val image = request?.let {
            MultipartBody.Part.createFormData(
                "image",
                "image.jpg",
                it
            )
        }*/

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

        cancelbtn.setOnClickListener {
            this.finish()
        }
    }
}