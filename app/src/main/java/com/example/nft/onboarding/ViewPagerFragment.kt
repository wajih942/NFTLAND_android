package com.example.nft.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.nft.R
import com.example.nft.onboarding.screens.FirstScreen
import com.example.nft.onboarding.screens.SecondScreen
import com.example.nft.onboarding.screens.ThirdScreen

class ViewPagerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)
        val fragmentList = arrayListOf<Fragment>(
            ThirdScreen(),
            SecondScreen(),
            FirstScreen()
        )
        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        val viewpager = view.findViewById<ViewPager2>(R.id.viewpager)
        viewpager.adapter = adapter
        return view
    }

}