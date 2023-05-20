package com.example.countrylist.repository

import com.example.countrylist.model.CountryItem
import com.example.countrylist.model.DataWrapper
import com.example.countrylist.network.CountriesAPI
import javax.inject.Inject

class CountryRepository @Inject constructor(private val api: CountriesAPI) {

    private var countryData = DataWrapper<ArrayList<CountryItem>, Boolean, Exception>(null, true, null)
    private var selectedCountryData = DataWrapper<ArrayList<CountryItem>, Boolean, Exception>(null, true, null)
    private var countryDataByLang = DataWrapper<ArrayList<CountryItem>, Boolean, Exception>(null, true, null)
    private var countryDataByRegion = DataWrapper<ArrayList<CountryItem>, Boolean, Exception>(null, true, null)

    suspend fun getAllCountries(): DataWrapper<ArrayList<CountryItem>, Boolean, Exception> {
        try {
            countryData = DataWrapper(api.getAllCountries(), false, null)
//            countryData.loading = true
//            countryData.data = api.getAllCountries()
//            if (countryData.data.toString().isNotEmpty()) countryData.loading = false
        } catch (e: Exception) {
            countryData = DataWrapper(null, false, e)
//            countryData.loading = false
//            countryData.e = e
        }
        return countryData
    }

    suspend fun getCountryByName(name: String): DataWrapper<ArrayList<CountryItem>, Boolean, Exception> {
        try {
            selectedCountryData = DataWrapper(api.getCountryByName(name), false, null)
//            selectedCountryData.loading = true
//            selectedCountryData.data = api.getCountryByName(name)
//            if (selectedCountryData.data.toString().isNotEmpty()) selectedCountryData.loading = false
        } catch (e: Exception) {
            selectedCountryData = DataWrapper(null, false, e)
//            selectedCountryData.loading = false
//            selectedCountryData.e = e
        }
        return selectedCountryData
    }

    suspend fun getCountryByLang(lang: String): DataWrapper<ArrayList<CountryItem>, Boolean, Exception> {
        try {
            countryDataByLang = DataWrapper(api.getCountryByLanguage(lang), false, null)
//            countryDataByLang.loading = true
//            countryDataByLang.data = api.getCountryByLanguage(lang)
//            if (countryDataByLang.data.toString().isNotEmpty()) countryDataByLang.loading = false
        } catch (e: Exception) {
            countryDataByLang = DataWrapper(null, false, e)
//            countryDataByLang.loading = false
//            countryDataByLang.e = e
        }
        return countryDataByLang
    }

    suspend fun getCountryByRegion(region: String): DataWrapper<ArrayList<CountryItem>, Boolean, Exception> {
        try {
            countryDataByRegion = DataWrapper(api.getCountryByRegion(region), false, null)
//            countryDataByRegion.loading = true
//            countryDataByRegion.data = api.getCountryByRegion(region)
//            if (countryDataByRegion.data.toString().isNotEmpty()) countryDataByRegion.loading = false
        } catch (e: Exception) {
            countryDataByRegion = DataWrapper(null, false, e)
//            countryDataByRegion.loading = false
//            countryDataByRegion.e = e
        }
        return countryDataByRegion
    }

}