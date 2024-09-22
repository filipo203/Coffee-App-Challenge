package com.example.coffeeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import com.example.coffeeapp.navigation.CoffeeAppNav
import com.example.coffeeapp.ui.theme.CoffeeAppTheme
import com.example.coffeeapp.viewmodel.CoffeeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val coffeeViewModel: CoffeeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeAppTheme {
                val coffees by coffeeViewModel.coffees
                val errorMessage by coffeeViewModel.errorMessage

                CoffeeAppNav(
                    coffees = coffees,
                    errorMessage = errorMessage
                )
            }
        }
    }
}