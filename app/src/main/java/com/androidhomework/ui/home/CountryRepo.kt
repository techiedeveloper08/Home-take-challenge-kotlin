package com.androidhomework.ui.home

import com.androidhomework.model.Country
import io.reactivex.disposables.Disposable

interface CountryRepo: Disposable {
    fun getCountriesData(onSuccess: (countryRes: List<Country>, numberOfAttempt: Int) -> Unit, onError:(error: Throwable, numberOfAttempt: Int) -> Unit)
}