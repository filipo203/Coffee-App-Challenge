package com.example.coffeeapp.retrofit

import retrofit2.http.GET

interface CoffeeApi {
    @GET("iced")
    suspend fun getIcedCoffee(): List<IcedCoffee>
}