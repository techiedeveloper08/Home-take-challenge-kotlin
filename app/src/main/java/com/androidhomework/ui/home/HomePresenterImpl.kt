package com.androidhomework.ui.home

import com.androidhomework.model.Country

class HomePresenterImpl(
        private val model: Home.Model,
        private val controller: Home.Controller)
    : Home.Presenter {

    override fun initialize() {
        model.getCountriesData(onSuccess = ::onCountriesDataSuccess, onFail = ::onCountriesDataFail)
    }

    private fun onCountriesDataSuccess(countriesRes: List<Country>, numberOfAttempt: Int) {
        controller.onCountriesDataSuccess(countriesRes, numberOfAttempt)
    }

    private fun onCountriesDataFail(errorMsg: String, numberOfAttempt: Int) {
        controller.onCountriesDataFail(errorMsg, numberOfAttempt)
    }
}