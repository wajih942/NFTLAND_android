package com.example.nft

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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
        var image1 = findViewById<TextView>(R.id.image)



        returnbtn.setOnClickListener {
            this.finish()
        }
        val name = intent.getStringExtra("name")
        val price = intent.getStringExtra("price")
        val description = intent.getStringExtra("desc")
        val seller = intent.getStringExtra("seller")
        val image = intent.getIntExtra("image",1)

        name1.setText(name)
        price1.setText(price + " ETH")
        desc1.setText(description)
        seller1.setText("Seller address : " + seller)
        image1.

    }
}