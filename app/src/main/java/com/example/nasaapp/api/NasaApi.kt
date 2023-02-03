package com.example.nasaapp.api

import com.example.nasaapp.models.ApodModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface NasaApi {

    @GET("planetary/apod?count=50&api_key=S9qWQgYBW1fCQepdp3f1eCWQv1YQb6J5YixQAD4B")
    fun getApod() : Call<List<ApodModel>>
}