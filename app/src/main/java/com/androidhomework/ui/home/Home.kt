package com.androidhomework.ui.home

import com.androidhomework.model.Country


interface Home {

    interface Model {
        fun getCountriesData(onSuccess: (countriesRes: List<Country>, numberOfAttempt: Int) -> Unit, onFail: (errorMsg: String, numberOfAttempt: Int) -> Unit)
    }

    interface Controller {
        fun onShowLoading(showLoading: Boolean)
        fun onCountriesDataSuccess(countriesRes: List<Country>,numberOfAttempt: Int)
        fun onCountriesDataFail(errorMsg: String, numberOfAttempt: Int)
    }

    interface Presenter {
        fun initialize()
    }
}