package com.example.nft

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nft.model.ItemInfo
import com.example.nft.model.MarketSale
import com.example.nft.repository.Repository

class MarketSaleActivity : AppCompatActivity() {
    lateinit var sharedPrefrences : SharedPreferences
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_sale)
        val etgasprice = findViewById<EditText>(R.id.et_gasprice)
        val etgaslimit = findViewById<EditText>(R.id.et_gasLimit)
        val buy = findViewById<Button>(R.id.buybtn)

        sharedPrefrences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        val address = sharedPrefrences.getString("address","").toString()
        val privateKey = sharedPrefrences.getString("private","").toString()
        val txhash = intent.getStringExtra("txhash")
        val price = intent.getStringExtra("price")
        Toast.makeText(this,txhash + " " + price,Toast.LENGTH_LONG).show()
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)


        buy.setOnClickListener {
            val marketSale = MarketSale(price.toString(),txhash.toString(),address,privateKey,etgaslimit.text.toString(),etgasprice.text.toString())
            viewModel.createsale(marketSale)


        }

        viewModel.myResponse2.observe(this, Observer { response ->
            if (response.isSuccessful){
                var intent = Intent(this,CheckActivity::class.java)
                intent.putExtra("txhash",response.body()?.txHash.toString())
                startActivity(intent)
            }else{
                Log.i("Response","error")

            }

        })

    }
}