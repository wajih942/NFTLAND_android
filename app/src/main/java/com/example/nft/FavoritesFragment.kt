package com.example.nft

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nft.adapter.MyAdapter1
import com.example.nft.adapter.MyAdapter2
import com.example.nft.data.FavViewModel
import com.example.nft.model.Item


class FavoritesFragment : Fragment() {

    private val myAdapter by lazy { MyAdapter2() }
    private lateinit var mFavViewModel : FavViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_favorites, container, false)
        val txt2 = view.findViewById<TextView>(R.id.left)
        txt2.setOnClickListener {
            //Navigation.findNavController(view).navigate(R.id.purshasedFragment)
            findNavController().navigate(R.id.action_favoritesFragment_to_purshasedFragment)

        }


        var recyclerView = view.findViewById<RecyclerView>(R.id.favrecycler)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(view.context,
            LinearLayoutManager.HORIZONTAL,false)


        mFavViewModel = ViewModelProvider(this).get(FavViewModel::class.java)
        mFavViewModel.readAllData.observe(viewLifecycleOwner, Observer { fav ->
            myAdapter.setData(fav)
        })

        return view
    }


}