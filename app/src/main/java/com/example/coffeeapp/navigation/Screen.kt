package com.example.coffeeapp.navigation

sealed class Screen(val route: String) {
    object ListScreen : Screen("coffeeList")
    object DetailScreen : Screen("coffeeDetail/{coffeeId}")
}
