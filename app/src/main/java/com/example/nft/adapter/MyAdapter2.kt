package com.example.nft.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Delete
import com.bumptech.glide.Glide
import com.example.nft.DeleteActivity
import com.example.nft.R
import com.example.nft.data.FavViewModel
import com.example.nft.data.FavoriteDatabase
import com.example.nft.data.FavoriteItem
import com.example.nft.model.Item

class MyAdapter2: RecyclerView.Adapter<MyAdapter2.MyViewHolder>() {

    private var nfts = emptyList<FavoriteItem>()




    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {

        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.favrow,parent,false))
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.itemView.findViewById<TextView>(R.id.nftdescription).text = nfts[position].desc
        holder.itemView.findViewById<TextView>(R.id.nftname).setText(nfts[position].name)
        holder.itemView.findViewById<TextView>(R.id.nftprice).setText( nfts[position].price + " ETH")
        Glide.with(holder.itemView).load(nfts[position].image).into(holder.itemView.findViewById(R.id.nftimage))

        holder.itemView.setOnClickListener {

            val intent = Intent(holder.itemView.context,DeleteActivity::class.java)
            intent.putExtra("id",nfts[position].id)
            intent.putExtra("name",nfts[position].name)
            intent.putExtra("price",nfts[position].price)
            intent.putExtra("desc",nfts[position].desc)
            intent.putExtra("image",nfts[position].image)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return nfts.size
    }
    fun setData(newList : List<FavoriteItem>){
        nfts = newList
        notifyDataSetChanged()
    }


}