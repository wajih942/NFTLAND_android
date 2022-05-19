package com.example.nft

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.nft.data.FavViewModel
import com.example.nft.data.FavoriteItem

class DeleteActivity : AppCompatActivity() {
    private lateinit var mFavViewModel : FavViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_delete)

        var returnbtn : Button = findViewById(R.id.btnreturn)
        var name1 = findViewById<TextView>(R.id.namedetails)
        var price1 = findViewById<TextView>(R.id.pricedetails)
        var desc1 = findViewById<TextView>(R.id.descriptiondetails)
        var image1 = findViewById<ImageView>(R.id.imagedetails)
        var deletebtn = findViewById<Button>(R.id.deletebtn)


        returnbtn.setOnClickListener {
            this.finish()
        }

        val name = intent.getStringExtra("name")
        val price = intent.getStringExtra("price")
        val description = intent.getStringExtra("desc")

        val id = intent.getIntExtra("id",0)
        val image = intent.getStringExtra("image")
        name1.setText(name)
        price1.setText(price + " ETH")
        desc1.setText(description)
        Glide.with(this).load(image).into(image1)
        mFavViewModel = ViewModelProvider(this).get(FavViewModel::class.java)
        val fav = FavoriteItem(id,name.toString(),description.toString(),price.toString(),image.toString())
        deletebtn.setOnClickListener {
            mFavViewModel.deleteFav(fav)
        }
    }
}