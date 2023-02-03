package com.example.nasaapp.models

import com.example.nasaapp.api.NasaApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val baseUrl = "https://api.nasa.gov/"

    private fun getInstance() : Retrofit
    {
        return  Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val requests: NasaApi by lazy {
        getInstance().create(NasaApi::class.java)
    }
}