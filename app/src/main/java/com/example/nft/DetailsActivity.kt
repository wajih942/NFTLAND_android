package com.example.nft

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {
    lateinit var sharedPrefrences : SharedPreferences
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

        sharedPrefrences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        val address = sharedPrefrences.getString("address","").toString()


        returnbtn.setOnClickListener {
            this.finish()
        }
        val name = intent.getStringExtra("name")
        val price = intent.getStringExtra("price")
        val description = intent.getStringExtra("desc")
        val seller = intent.getStringExtra("seller")
        val image = intent.getStringExtra("image")
        val token = intent.getStringExtra("token")


        buybtn.setOnClickListener {
            if(address == ""){
                Toast.makeText(this,"Please connect your wallet to buy any item",Toast.LENGTH_SHORT).show()
            }else{
                if (address == seller){
                    Toast.makeText(this,"you can't buy your own item",Toast.LENGTH_SHORT).show()
                }else{
                    var intent = Intent(it.context,WalletActivity::class.java)
                    intent.putExtra("price",price.toString())
                    intent.putExtra("token",token.toString())
                    startActivity(intent)

                }
            }

        }









        name1.setText(name)
        price1.setText(price + " ETH")
        desc1.setText(description)
        seller1.setText("Seller address : " + seller)
        Glide.with(this).load(image).into(image1)



    }
}