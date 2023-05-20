package com.example.countrylist.screens.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrylist.model.CountryItem
import com.example.countrylist.model.DataWrapper
import com.example.countrylist.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesScreenViewModel @Inject constructor(private val repository: CountryRepository) :
    ViewModel() {

    val countries: MutableState<DataWrapper<ArrayList<CountryItem>, Boolean, Exception>> =
        mutableStateOf(DataWrapper(null, false, null))
    val countriesByLang: MutableState<DataWrapper<ArrayList<CountryItem>, Boolean, Exception>> =
        mutableStateOf(DataWrapper(null, false, null))
    val countriesByRegion: MutableState<DataWrapper<ArrayList<CountryItem>, Boolean, Exception>> =
        mutableStateOf(DataWrapper(null, false, null))

    init {
        getAllCountries()
    }

    private fun getAllCountries() {
        viewModelScope.launch {
            countries.value = repository.getAllCountries()
        }
    }

    fun getCountriesByLanguage(lang: String) {
        viewModelScope.launch {
            countriesByLang.value = repository.getCountryByLang(lang)
        }
    }

    fun getCountriesByRegion(region: String) {
        viewModelScope.launch {
            countriesByRegion.value = repository.getCountryByRegion(region)
        }
    }

}