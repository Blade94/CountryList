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
class CountryDetailsScreenViewModel @Inject constructor(private val repository: CountryRepository): ViewModel() {

    val selectedCountry: MutableState<DataWrapper<ArrayList<CountryItem>, Boolean, Exception>> = mutableStateOf(DataWrapper(null, true, null))

    fun getSelectedCountry(name: String) {
        viewModelScope.launch {
            selectedCountry.value.loading = true
            selectedCountry.value = repository.getCountryByName(name)
            if (selectedCountry.value.data.toString().isNotEmpty()) {
                selectedCountry.value.loading = false
            }
        }
    }

}