package com.example.nft

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nft.model.ItemInfo
import com.example.nft.repository.Repository

class WalletActivity : AppCompatActivity() {
    lateinit var sharedPrefrences : SharedPreferences
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)
        val etgasprice = findViewById<EditText>(R.id.et_gasprice)
        val etgasLimit = findViewById<EditText>(R.id.et_gasLimit)
        val progress = findViewById<ProgressBar>(R.id.progresscircle)

        val itemprice = findViewById<TextView>(R.id.itemprice)
        var cancelbtn = findViewById<Button>(R.id.cancelbtn)
        var buybtn = findViewById<Button>(R.id.buybtn)
        cancelbtn.setOnClickListener {
            this.finish()
        }
        sharedPrefrences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        val address = sharedPrefrences.getString("address","").toString()
        val privateKey = sharedPrefrences.getString("private","").toString()
        val price = intent.getStringExtra("price").toString()
        val token = intent.getStringExtra("token").toString()
        Toast.makeText(this,address +"  " + price + " " + privateKey + " " + token,Toast.LENGTH_SHORT).show()
        itemprice.text = price + " ETH"
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)


        buybtn.setOnClickListener {
            val itemInfo = ItemInfo(price,token,address,privateKey,etgasLimit.text.toString(),etgasprice.text.toString())
            viewModel.buynft(itemInfo)
            progress.isVisible = true

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