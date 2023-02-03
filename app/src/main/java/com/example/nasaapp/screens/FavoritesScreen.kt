package com.example.nasaapp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.nasaapp.R
import com.example.nasaapp.database.DBHelper
import com.example.nasaapp.screens.adapters.FavoriteAdapter


class FavoritesScreen : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorites_screen, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.rvSavedData)
        val swipeRefreshLayout: SwipeRefreshLayout = view.findViewById(R.id.swipe)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val dbHelper = DBHelper(requireContext())
        val dataList = dbHelper.viewData()

        if (dataList.size > 0) {
            val adapter = FavoriteAdapter(dataList, requireContext())
            recyclerView.adapter = adapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            val renewedData = dbHelper.viewData()

            if (renewedData.size > 0)
            {
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                val adapter = FavoriteAdapter(renewedData, requireContext())
                recyclerView.adapter = adapter
            }
        }

        return view
    }


}