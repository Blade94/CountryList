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
class CountriesScreenViewModel @Inject constructor(private val repository: CountryRepository) :ViewModel() {

    val countries: MutableState<DataWrapper<ArrayList<CountryItem>, Boolean, Exception>> = mutableStateOf(DataWrapper(null, false, null))
    //TODO aggiungere set per salvare le lingue
    //TODO aggiungere set per salvare le regioni (continenti)

    init {
        getAllCountries()
    }

    private fun getAllCountries() {
        viewModelScope.launch {
            countries.value.loading = true
            countries.value = repository.getAllCountries()
            if (countries.value.data.toString().isNotEmpty()) {
                countries.value.loading = false
            }
        }
    }

}