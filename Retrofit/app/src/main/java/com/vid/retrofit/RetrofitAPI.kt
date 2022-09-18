package com.vid.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitAPI {

    @GET("/posts")
    fun getAllPost(): Call<List<Posts>>


}