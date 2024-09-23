package com.example.coffeeapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeapp.R
import com.example.coffeeapp.retrofit.IcedCoffee

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeListScreen(
    coffees: List<IcedCoffee>,
    errorMessage: String?,
    onCoffeeClick: (IcedCoffee) -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxWidth(), topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    stringResource(R.string.app_name), fontWeight = FontWeight.Bold
                )
            }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            )
        )
    }) {
        if (errorMessage != null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyColumn(modifier = Modifier.padding(top = 60.dp)) {
                items(coffees) { coffee ->
                    CoffeeListItem(coffee = coffee, onClick = { onCoffeeClick(coffee) })
                }
            }
        }
    }
}

@Composable
fun CoffeeListItem(
    coffee: IcedCoffee,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .animateContentSize(
                    animationSpec = tween(
                        easing = LinearOutSlowInEasing
                    )
                )
                .clickable { onClick() }) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(coffee.title, fontWeight = FontWeight.Bold, fontSize = 28.sp)
                if (coffee.ingredients.any { it.contains("Sugar") }) {
                    Image(
                        painter = painterResource(id = R.drawable.sugar_512x512),
                        contentDescription = "Sugar",
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
            Text(coffee.description, fontSize = 14.sp, modifier = Modifier.padding(8.dp))
        }
    }
}