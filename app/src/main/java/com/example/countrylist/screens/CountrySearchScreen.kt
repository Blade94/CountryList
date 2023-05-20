package com.example.countrylist.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavController
import com.example.countrylist.navigation.CountriesScreens
import com.example.countrylist.screens.viewmodel.CountriesScreenViewModel
import com.example.countrylist.ui.component.CountryListCard
import com.example.countrylist.ui.component.SearchTextField

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CountrySearchScreen(
    navController: NavController,
    countriesScreenViewModel: CountriesScreenViewModel
) {
    val textFieldState = remember {
        mutableStateOf("")
    }
    val validState = remember(textFieldState.value) {
        textFieldState.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Search") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        )
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
        ) {
            SearchTextField(
                textFieldState = textFieldState,
                validState = validState,
                keyboardController = keyboardController
            ) { langOrContinent ->
                countriesScreenViewModel.getCountriesByLanguage(langOrContinent)
//                countriesScreenViewModel.getCountriesByRegion(langOrContinent)
            }
            LazyColumn {
                items(
                    items = countriesScreenViewModel.countriesByLang.value.data ?: emptyList()
                ) { country ->
                    CountryListCard(country = country) { countryName ->
                        navController.navigate(CountriesScreens.CountryDetailsScreen.name + "/$countryName")
                    }
                }
            }
        }
    }
}