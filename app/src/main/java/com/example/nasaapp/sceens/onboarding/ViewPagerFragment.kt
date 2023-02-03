package com.example.nasaapp.sceens.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.nasaapp.R
import com.example.nasaapp.sceens.DetailsScreen
import com.example.nasaapp.sceens.FavoritesScreen
import com.example.nasaapp.sceens.MainScreen


class ViewPagerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)
        val fragmentList = arrayListOf<Fragment>(
            MainScreen(),
            FavoritesScreen()
        )

        val adapter = ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)

        view.findViewById<ViewPager2>(R.id.viewPager).adapter = adapter

        return  view
    }


}