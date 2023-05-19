package com.example.countrylist.network

import com.example.countrylist.model.Country
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface CountriesAPI {

    @GET("all")
    suspend fun getAllCountries(): Country

}