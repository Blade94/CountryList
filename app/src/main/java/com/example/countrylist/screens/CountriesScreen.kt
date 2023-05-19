package com.example.countrylist.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.countrylist.screens.viewmodel.CountriesScreenViewModel
import com.example.countrylist.ui.component.CountryListCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(
    navController: NavController,
    countriesScreenViewModel: CountriesScreenViewModel
) {
    Scaffold() {
        Column(modifier = Modifier.padding(it)) {
            TopAppBar(
                title = { Text(text = "Country List") },
                actions = {
                    IconButton(onClick = { /*TODO fare la funzionalità di ricerca*/ }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
            if (!countriesScreenViewModel.countries.value.data.isNullOrEmpty()) {
                LazyColumn {
                    items(items = countriesScreenViewModel.countries.value.data!!.toList()) {
                        country ->
                        CountryListCard(country = country)
                    }
                }
            } else {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    Text(text = "No Countries found!")
                }
            }
        }
    }
}