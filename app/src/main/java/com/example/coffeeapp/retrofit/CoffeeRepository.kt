package com.example.coffeeapp.retrofit

import javax.inject.Inject

class CoffeeRepository @Inject constructor(
    private val coffeeApi: CoffeeApi
) {
    suspend fun getIcedCoffee(): List<IcedCoffee> {
        return coffeeApi.getIcedCoffee()
    }
}