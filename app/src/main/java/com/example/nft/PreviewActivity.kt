package com.example.nft

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class PreviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        val image = findViewById<ImageView>(R.id.imageprev)
        val returnbtn =findViewById<ImageView>(R.id.returnprev)
        val continuebtn = findViewById<Button>(R.id.continueprev)
        val title = findViewById<TextView>(R.id.titleprev)
        val price = findViewById<TextView>(R.id.priceprev)
        val desc = findViewById<TextView>(R.id.descriptionprev)
        val size = findViewById<TextView>(R.id.sizeprev)
        returnbtn.setOnClickListener {
            this.finish()
        }



        val st = intent.getStringExtra("uri")
        val uri : Uri = Uri.parse(st)
        image.setImageURI(uri)

        val title1 = intent.getStringExtra("title")
        title.setText(title1)
        val  price1 = intent.getStringExtra("price")
        price.setText(price1 + " ETH")
        val desc1 = intent.getStringExtra("desc")
        desc.setText(desc1)
        val size1 = intent.getStringExtra("size")
        size.setText("size : " + size1 + "px")
        continuebtn.setOnClickListener {

            val intent = Intent(this,MintNftActivity::class.java)
            intent.putExtra("uri",st.toString())
            intent.putExtra("title",title1.toString())
            intent.putExtra("price",price1.toString())
            intent.putExtra("desc",desc1.toString())
            intent.putExtra("size",size1)
            startActivity(intent)
        }

    }
}