package com.androidhomework

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.androidhomework.model.Country
import com.androidhomework.model.Notes
import com.androidhomework.ui.home.CountryRepo
import io.reactivex.Observable

class TestCountryRepo: CountryRepo {

    private val gson = Gson()

    private val countryList: List<Country>
        get() {
            return gson.fromJson(TestCountry.countries, object : TypeToken<List<Country>>() {}.type)
        }

    override fun getCountriesData(onSuccess: (countryRes: List<Country>, numberOfAttempt: Int) -> Unit, onError: (error: Throwable, numberOfAttempt: Int) -> Unit) {
        Observable.just(countryList).subscribe({
            onSuccess(it, 2)
        }, {
            onError(it, 2)
        })
    }

    override fun isDisposed(): Boolean {
        TODO("Not yet implemented")
    }

    override fun dispose() {
        TODO("Not yet implemented")
    }
}