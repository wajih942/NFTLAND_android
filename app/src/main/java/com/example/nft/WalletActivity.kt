package com.example.nft

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class WalletActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)


        var cancelbtn = findViewById<Button>(R.id.cancelbtn)
        var buybtn = findViewById<Button>(R.id.buybtn)
        cancelbtn.setOnClickListener {
            this.finish()
        }
        buybtn.setOnClickListener {
            var intent = Intent(it.context,CheckActivity::class.java)
            startActivity(intent)
        }
    }
}