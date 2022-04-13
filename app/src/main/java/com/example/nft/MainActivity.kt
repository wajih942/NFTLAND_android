package com.example.nft

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nft.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text = findViewById<TextView>(R.id.tv5)
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getItem()
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful){
                response.body()?.forEach {
                    Log.i("Response",it.tokenId.toString())
                    Log.i("Response",it.seller.toString())
                    Log.i("Response",it.owner.toString())
                    Log.i("Response",it.price.toString())
                    Log.i("Response",it.name.toString())
                    Log.i("Response",it.description.toString())
                    Log.i("Response",it.image.toString())
                    Log.i("Response","------------------------------")

                }
                Toast.makeText(this,"succes",Toast.LENGTH_SHORT).show()

            }else{
                Log.i("Response","error")

            }

        })
    }
}