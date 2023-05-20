package com.example.countrylist.model

data class DataWrapper<T, Boolean, Exception>(val data: T?, val loading: Boolean?, val e: Exception?)
