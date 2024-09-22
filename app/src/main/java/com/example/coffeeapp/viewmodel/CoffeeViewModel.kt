package com.example.coffeeapp.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.retrofit.CoffeeRepository
import com.example.coffeeapp.retrofit.IcedCoffee
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeViewModel @Inject constructor(
    private val repository: CoffeeRepository
) : ViewModel() {

    private val _coffees = mutableStateOf<List<IcedCoffee>>(emptyList())
    val coffees: State<List<IcedCoffee>> = _coffees

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    init {
        getIcedCoffee()
    }

    private fun getIcedCoffee() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _coffees.value = repository.getIcedCoffee()
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Network error! Failed to get Iced Coffee: ${e.message}"
                Log.e("CoffeeViewModel", _errorMessage.value ?: "Unknown error")
            }
        }
    }
}