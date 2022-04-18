package com.example.nft

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        var returnbtn : Button = findViewById(R.id.btnreturn)
        var name1 = findViewById<TextView>(R.id.namedetails)
        var price1 = findViewById<TextView>(R.id.pricedetails)
        var desc1 = findViewById<TextView>(R.id.descriptiondetails)
        var seller1 = findViewById<TextView>(R.id.sellerdetails)
        var image1 = findViewById<ImageView>(R.id.imagedetails)



        returnbtn.setOnClickListener {
            this.finish()
        }
        val name = intent.getStringExtra("name")
        val price = intent.getStringExtra("price")
        val description = intent.getStringExtra("desc")
        val seller = intent.getStringExtra("seller")



        var bitmap : Bitmap? = intent.getByteArrayExtra("image")
            ?.let { BitmapFactory.decodeByteArray(intent.getByteArrayExtra("image"),0 , it.size) }








        name1.setText(name)
        price1.setText(price + " ETH")
        desc1.setText(description)
        seller1.setText("Seller address : " + seller)
        image1.setImageBitmap(bitmap)


    }
}