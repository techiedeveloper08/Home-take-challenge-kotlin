package com.androidhomework.api

import com.androidhomework.model.Country
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApi {

    @GET("countries")
    fun getCountries(): Observable<Response<List<Country>>>
}