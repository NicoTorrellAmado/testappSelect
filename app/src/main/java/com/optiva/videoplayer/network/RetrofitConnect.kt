package com.optiva.videoplayer.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConnect {

    private var retrofit: Retrofit? = null;
    private val url = "https://drive.google.com/"

    val retrofitInst: Retrofit?
    get(){
        if(retrofit == null){
            retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()

        }
        return retrofit
    }

}