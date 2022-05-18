package com.example.nft

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.EditText

import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.doOnTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nft.adapter.MyAdapter
import com.example.nft.model.Item
import com.example.nft.repository.Repository
import com.google.android.material.navigation.NavigationView
import com.google.android.material.textfield.TextInputLayout
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    private lateinit var viewModel: MainViewModel
    private val myAdapter by lazy { MyAdapter() }
    private lateinit var newitemArrayList : ArrayList<Item>
    private lateinit var tempitemArrayList : ArrayList<Item>
    lateinit var sharedPrefrences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_main)
        val intent = Intent(this,ItemssActivity::class.java)
        startActivity(intent)

    }


}