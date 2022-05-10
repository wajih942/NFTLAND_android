package com.example.nft

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class CheckActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)
        val txhash = intent.getStringExtra("txhash")
        val hash = findViewById<TextView>(R.id.hash)
        val copy = findViewById<Button>(R.id.copybtn)
        hash.text = txhash.toString()
        Toast.makeText(this,txhash,Toast.LENGTH_SHORT).show()
        copy.setOnClickListener {
            copyToClipboard(hash.text.toString())
        }
    }
    fun Context.copyToClipboard(text: CharSequence){
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label",text)
        clipboard.setPrimaryClip(clip)
    }
}