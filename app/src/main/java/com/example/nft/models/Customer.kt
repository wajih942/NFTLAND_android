package com.example.nft.models

data class Customer(
    var _id  : String,
    var name: String,
    var url: String,
    var bio: String,
    var email: String,
    var password: String,
    var wallet_address: String,
    var profile_picture:String,
    val followers: Array<String>,
    val following: Array<String>
)
