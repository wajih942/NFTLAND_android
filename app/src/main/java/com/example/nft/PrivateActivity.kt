package com.example.nft

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class PrivateActivity : AppCompatActivity() {
    lateinit var sharedPrefrences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_private)
        val address = intent.getStringExtra("address")
        val private = findViewById<TextView>(R.id.et_private)
        val continuebtn = findViewById<Button>(R.id.continuebtn)

        sharedPrefrences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)

        continuebtn.setOnClickListener {
            val editor : SharedPreferences.Editor = sharedPrefrences.edit()
            editor.putString("address",address)
            editor.putString("private",private.text.toString())
            editor.apply()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}