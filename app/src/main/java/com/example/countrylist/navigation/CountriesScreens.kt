package com.example.countrylist.navigation

enum class CountriesScreens {
    CountriesScreen,
    CountryDetailsScreen,
    CountrySearchScreen;

    companion object {
        fun fromRoute(route: String?): CountriesScreens = when (route?.substringBefore("/")) {
            CountriesScreen.name -> CountriesScreen
            CountryDetailsScreen.name -> CountryDetailsScreen
            CountrySearchScreen.name -> CountrySearchScreen
            null -> CountriesScreen
            else -> throw IllegalStateException("Destination $route not found")
        }
    }
}