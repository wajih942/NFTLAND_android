package com.example.nft.onboarding.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.nft.InfoActivity
import com.example.nft.KeyActivity
import com.example.nft.R


class FirstScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_first_screen, container, false)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewpager)

        val iagree = view.findViewById<CheckBox>(R.id.iagreecheckbox)
        val next = view.findViewById<TextView>(R.id.continuebtn)
        next.setOnClickListener {
            if(iagree.isChecked){
                val intent = Intent(context, InfoActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(context,"you should agree to terms before continuing", Toast.LENGTH_SHORT).show()
            }

        }
        return view
    }

}