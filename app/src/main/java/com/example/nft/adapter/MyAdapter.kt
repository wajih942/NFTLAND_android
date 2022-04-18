package com.example.nft.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nft.DetailsActivity
import com.example.nft.R
import com.example.nft.model.Item

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var nfts = emptyList<Item>()

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {



        holder.itemView.findViewById<TextView>(R.id.nftdescription).text = nfts[position].name
        holder.itemView.findViewById<TextView>(R.id.nftprice).text = nfts[position].price + " ETH"
        Glide.with(holder.itemView).load(nfts[position].image).into(holder.itemView.findViewById(R.id.nftimage))
        holder.itemView.findViewById<LinearLayout>(R.id.row).setOnClickListener {
            Toast.makeText(holder.itemView.context,"ok",Toast.LENGTH_SHORT).show()
            var intent = Intent(it.context,DetailsActivity::class.java)
            intent.putExtra("name",nfts[position].name)
            intent.putExtra("price",nfts[position].price)
            intent.putExtra("desc",nfts[position].description)
            intent.putExtra("owner",nfts[position].seller)
            intent.putExtra("image",nfts[position].image)


            it.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return nfts.size
    }
    fun setData(newList : List<Item>){
        nfts = newList
        notifyDataSetChanged()
    }
}