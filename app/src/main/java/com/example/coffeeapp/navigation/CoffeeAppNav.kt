package com.example.coffeeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.coffeeapp.retrofit.IcedCoffee
import com.example.coffeeapp.ui.screens.CoffeeDetailScreen
import com.example.coffeeapp.ui.screens.CoffeeListScreen

@Composable
fun CoffeeAppNav(
    coffees: List<IcedCoffee>,
    errorMessage: String?,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Screen.ListScreen.route) {
        composable(Screen.ListScreen.route) {
            CoffeeListScreen(coffees, errorMessage) { coffee ->
                navController.navigate("coffeeDetail/${coffee.id}")
            }
        }
        composable(
            Screen.DetailScreen.route,
            arguments = listOf(navArgument("coffeeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val coffeeId = backStackEntry.arguments?.getInt("coffeeId") ?: 0
            val coffee = coffees.find { it.id == coffeeId }
            coffee?.let {
                CoffeeDetailScreen(coffee = it) { navController.popBackStack() }
            }
        }
    }
}