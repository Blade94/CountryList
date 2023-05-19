package com.example.countrylist.model

data class DataWrapper<T, Boolean, Exception>(var data: T? = null, var loading: Boolean? = null, var e: Exception? = null)
