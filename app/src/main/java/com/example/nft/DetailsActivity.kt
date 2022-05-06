package com.example.nft

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_details)
        var returnbtn : Button = findViewById(R.id.btnreturn)
        var name1 = findViewById<TextView>(R.id.namedetails)
        var price1 = findViewById<TextView>(R.id.pricedetails)
        var desc1 = findViewById<TextView>(R.id.descriptiondetails)
        var seller1 = findViewById<TextView>(R.id.sellerdetails)
        var image1 = findViewById<ImageView>(R.id.imagedetails)
        var buybtn = findViewById<Button>(R.id.buydetails)



        returnbtn.setOnClickListener {
            this.finish()
        }
        val name = intent.getStringExtra("name")
        val price = intent.getStringExtra("price")
        val description = intent.getStringExtra("desc")
        val seller = intent.getStringExtra("seller")
        val image = intent.getStringExtra("image")

        buybtn.setOnClickListener {
            var intent = Intent(it.context,WalletActivity::class.java)
            startActivity(intent)
        }









        name1.setText(name)
        price1.setText(price + " ETH")
        desc1.setText(description)
        seller1.setText("Seller address : " + seller)
        Glide.with(this).load(image).into(image1)



    }
}