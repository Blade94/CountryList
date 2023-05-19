package com.example.countrylist.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.countrylist.screens.CountriesScreen
import com.example.countrylist.screens.CountryDetailsScreen
import com.example.countrylist.screens.viewmodel.CountriesScreenViewModel

@Composable
fun CountriesNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = CountriesScreens.CountriesScreen.name
    ) {
        composable(route = CountriesScreens.CountriesScreen.name) {
            val countriesScreenViewModel = hiltViewModel<CountriesScreenViewModel>()
            CountriesScreen(navController, countriesScreenViewModel)
        }
        composable(route = CountriesScreens.CountryDetailsScreen.name + "/{name}",
            arguments = listOf(navArgument(name = "name") { type = NavType.StringType })
        ) {
            CountryDetailsScreen(navController, it.arguments?.getString("name"))
        }
    }
}