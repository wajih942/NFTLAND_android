package com.example.nft.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nft.DetailsActivity
import com.example.nft.R
import com.example.nft.model.Item

class MyAdapter1 : RecyclerView.Adapter<MyAdapter1.MyViewHolder>() {
    private var nfts = emptyList<Item>()


    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.purshased_layout,parent,false))
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.nftdescription).text = nfts[position].description
        holder.itemView.findViewById<TextView>(R.id.nftname).setText(nfts[position].name)
        holder.itemView.findViewById<TextView>(R.id.nftprice).setText( nfts[position].price + " ETH")
        Glide.with(holder.itemView).load(nfts[position].image).into(holder.itemView.findViewById(R.id.nftimage))
    }

    override fun getItemCount(): Int {
        return nfts.size
    }
    fun setData(newList : List<Item>){
        nfts = newList
        notifyDataSetChanged()
    }




}