package com.example.countrylist.repository

import com.example.countrylist.model.CountryItem
import com.example.countrylist.model.DataWrapper
import com.example.countrylist.network.CountriesAPI
import javax.inject.Inject

class CountryRepository @Inject constructor(private val api: CountriesAPI) {

    private val countryData = DataWrapper<ArrayList<CountryItem>, Boolean, Exception>()

    suspend fun getAllCountries(): DataWrapper<ArrayList<CountryItem>, Boolean, Exception> {
        try {
            countryData.loading = true
            countryData.data = api.getAllCountries()
            if (countryData.data.toString().isNotEmpty()) countryData.loading = false
        } catch (e: Exception) {
            countryData.loading = false
            countryData.e = e
        }
        return countryData
    }

}