package com.example.nasaapp.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaapp.R
import com.example.nasaapp.api.NasaApi
import com.example.nasaapp.models.ApodModel
import com.example.nasaapp.models.RetrofitInstance
import com.example.nasaapp.screens.adapters.NasaAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.HttpRetryException



class MainScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_main_screen, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.rvApiData)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        val call = RetrofitInstance.requests.getApod()

        call.enqueue(object : Callback<List<ApodModel>> {
            override fun onResponse(
                call: Call<List<ApodModel>>,
                response: Response<List<ApodModel>>
            ) {
                when (response.isSuccessful) {
                    true -> {
                        val articles = response.body()!!
                        recyclerView.adapter =
                            NasaAdapter(articles, requireContext())
                    }
                    false -> Toast.makeText(
                        view.context,
                        "Unsuccessfully response",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }

            override fun onFailure(call: Call<List<ApodModel>>, t: Throwable) {
                Toast.makeText(view.context, "Request fail", Toast.LENGTH_LONG).show()
            }

        })


        return view
    }

}