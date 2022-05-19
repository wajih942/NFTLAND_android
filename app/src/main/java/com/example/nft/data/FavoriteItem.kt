package com.example.nft.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_table")
data class FavoriteItem(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name: String ,
    val desc: String,
    val price: String ,
    val image :String)