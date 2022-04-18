package com.example.nft

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nft.adapter.MyAdapter
import com.example.nft.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val myAdapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_search)
        setupRecyclerview()
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getItem()
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful){
                response.body()?.let { myAdapter.setData(it) }
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

            }else{
                Log.i("Response","error")

            }

        })
    }
    private fun setupRecyclerview(){
        var recyclerView = findViewById<RecyclerView>(R.id.nftsRecycler)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}