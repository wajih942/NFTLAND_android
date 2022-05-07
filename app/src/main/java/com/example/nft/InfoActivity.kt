package com.example.nft

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nft.repository.Repository

class InfoActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_info)

        val address = findViewById<EditText>(R.id.et_address)
        val continuebtn = findViewById<Button>(R.id.continuebtn)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

        continuebtn.setOnClickListener {
            viewModel.getBalance(address.text.toString())
        }


        viewModel.myResponse1.observe(this, Observer { response ->
            if (response.isSuccessful){
                if(response.body()?.err == null){
                    val intent = Intent(this,PrivateActivity::class.java)
                    intent.putExtra("address",address.text.toString())
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this,"please enter a valid address",Toast.LENGTH_SHORT).show()
                }
            }else{
                Log.i("Response","error")

            }

        })
    }
}