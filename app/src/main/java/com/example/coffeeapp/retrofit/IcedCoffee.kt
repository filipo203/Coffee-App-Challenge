package com.example.coffeeapp.retrofit

data class IcedCoffee(
    val title: String,
    val description: String,
    val ingredients: List<String>,
    val image: String,
    val id: Int
)
