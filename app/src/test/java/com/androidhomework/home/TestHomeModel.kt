package com.androidhomework.home

import com.androidhomework.model.Country
import com.androidhomework.ui.home.Home
import com.androidhomework.ui.home.HomeModelImpl

class TestHomeModel(private val homeModelImpl: HomeModelImpl) : Home.Model by homeModelImpl {

    override fun getCountriesData(onSuccess: (countriesRes: List<Country>, numberOfAttempt: Int) -> Unit, onFail: (errorMsg: String, numberOfAttempt: Int) -> Unit) {
        homeModelImpl.getCountriesData(onSuccess, onFail)
    }
}