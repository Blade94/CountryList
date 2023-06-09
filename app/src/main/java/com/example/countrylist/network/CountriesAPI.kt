package com.example.countrylist.network

import com.example.countrylist.model.Country
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface CountriesAPI {

    @GET("all")
    suspend fun getAllCountries(): Country

    @GET("name/{name}?fullText=true")
    suspend fun getCountryByName(@Path("name") name: String): Country

    @GET("lang/{currency}")
    suspend fun getCountryByLanguage(@Path("currency") currency: String): Country

    @GET("region/{region}")
    suspend fun getCountryByRegion(@Path("region") region: String): Country

}