package com.example.tdl

import com.example.tdl.Currency
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ICurrency {
    @GET("/v6/43fbc8a2f40714e3c6571ca2/latest/USD")
    suspend fun getCurrency(@Query("key") key: String): Response<Currency>
}