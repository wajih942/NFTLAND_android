package com.example.nft

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        var returnbtn : Button = findViewById(R.id.btnreturn)
        val intent = Intent()

        returnbtn.setOnClickListener {
            this.finish()
        }
        val name = intent.getStringExtra("name")
        val price = intent.getStringExtra("price")
        val description = intent.getStringExtra("desc")
        val seller = intent.getStringExtra("seller")
        val image = intent.getIntExtra("image",0)

    }
}