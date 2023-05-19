package com.example.countrylist.di

import com.example.countrylist.network.CountriesAPI
import com.example.countrylist.repository.CountryRepository
import com.example.countrylist.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCountriesApi(): CountriesAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesCountryRepository(api: CountriesAPI): CountryRepository {
        return CountryRepository(api)
    }

}