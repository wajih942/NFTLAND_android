package com.example.nft

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nft.adapter.MyAdapter
import com.example.nft.model.Item
import com.example.nft.repository.Repository
import com.google.android.material.navigation.NavigationView
import java.util.*
import kotlin.collections.ArrayList

class SearchActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    private lateinit var viewModel: MainViewModel
    private val myAdapter by lazy { MyAdapter() }
    private lateinit var newitemArrayList : ArrayList<Item>
    private lateinit var tempitemArrayList : ArrayList<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_search)
        var tool = findViewById<Toolbar>(R.id.toolbarb)
        setSupportActionBar(tool)
        //drawer
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)


        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.profile -> goToProfile()
                R.id.search -> Toast.makeText(this,"you are already on search", Toast.LENGTH_SHORT).show()
            }
            true
        }
        setupRecyclerview()
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        newitemArrayList = arrayListOf<Item>()
        tempitemArrayList = arrayListOf<Item>()
        viewModel.getItem()
        viewModel.myResponse.observe(this, androidx.lifecycle.Observer { response ->
            if (response.isSuccessful){

                response.body()?.forEach {
                    newitemArrayList.add(it)
                    Log.i("Response",it.tokenId.toString())
                    Log.i("Response",it.seller.toString())
                    Log.i("Response",it.owner.toString())
                    Log.i("Response",it.price.toString())
                    Log.i("Response",it.name.toString())
                    Log.i("Response",it.description.toString())
                    Log.i("Response",it.image.toString())
                    Log.i("Response","------------------------------")

                }
                tempitemArrayList.addAll(newitemArrayList)
                response.body()?.let { myAdapter.setData(tempitemArrayList) }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar,menu)
        val item = menu?.findItem(R.id.recherche)
        val searchView = item?.actionView as SearchView
        var recyclerView = findViewById<RecyclerView>(R.id.nftsRecycler)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                tempitemArrayList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if(searchText.isNotEmpty()){
                    newitemArrayList.forEach {
                        if (it.name.toLowerCase(Locale.getDefault()).contains(searchText)){
                            tempitemArrayList.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                }else{
                    tempitemArrayList.clear()
                    tempitemArrayList.addAll(newitemArrayList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }
    fun goToProfile(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}