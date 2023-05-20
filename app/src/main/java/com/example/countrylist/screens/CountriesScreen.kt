package com.example.countrylist.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.countrylist.navigation.CountriesScreens
import com.example.countrylist.screens.viewmodel.CountriesScreenViewModel
import com.example.countrylist.ui.component.CountryListCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(
    navController: NavController,
    countriesScreenViewModel: CountriesScreenViewModel
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Country List") },
            actions = {
                IconButton(onClick = { navController.navigate(CountriesScreens.CountrySearchScreen.name) }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        )
    }) {
        if (!countriesScreenViewModel.countries.value.data.isNullOrEmpty()) {
            LazyColumn(modifier = Modifier.padding(it)) {
                items(items = countriesScreenViewModel.countries.value.data!!.toList()) { country ->
                    CountryListCard(country = country) { countryName ->
                        navController.navigate(CountriesScreens.CountryDetailsScreen.name + "/$countryName")
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(strokeWidth = 5.dp, modifier = Modifier.size(50.dp))
            }
        }
    }
}