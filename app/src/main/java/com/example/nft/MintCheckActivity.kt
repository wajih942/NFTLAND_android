package com.example.nft

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.nft.R

class MintCheckActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mint_check)
        val hash = findViewById<TextView>(R.id.hash)
        val continuebtn = findViewById<Button>(R.id.continuebtn)
        val copybtn = findViewById<Button>(R.id.copybtn)
        val txhash = intent.getStringExtra("txhash")
        val price = intent.getStringExtra("price")
        hash.text = txhash
        continuebtn.setOnClickListener {
            val intent = Intent(this,MarketSaleActivity::class.java)
            intent.putExtra("txhash",txhash.toString())
            intent.putExtra("price",price.toString())
            startActivity(intent)
            finish()

        }
        copybtn.setOnClickListener {
            copyToClipboard(txhash.toString())
        }
    }
    fun Context.copyToClipboard(text: CharSequence){
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label",text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this,"copied succesfully", Toast.LENGTH_LONG).show()
    }
}