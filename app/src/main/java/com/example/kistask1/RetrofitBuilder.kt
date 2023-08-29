package com.example.kistask1

import com.examle.kistask1.Interface.RetrofitAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val url : String = "http://192.168.0.32:3000/"
    lateinit var api : RetrofitAPI

    init{
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(RetrofitAPI::class.java)
    }

}