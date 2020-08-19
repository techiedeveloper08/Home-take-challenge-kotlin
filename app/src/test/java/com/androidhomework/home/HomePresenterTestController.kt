package com.androidhomework.home

import com.androidhomework.TestCountryRepo
import com.androidhomework.model.Country
import com.androidhomework.ui.home.Home
import com.androidhomework.ui.home.HomeModelImpl
import com.androidhomework.ui.home.HomePresenterImpl
import org.junit.Assert

class HomePresenterTestController {

    private val countryRepo: TestCountryRepo = TestCountryRepo()

    private val theModel: TestHomeModel = TestHomeModel(homeModelImpl = HomeModelImpl(countryRepo = countryRepo))

    private var countriesList: List<Country>? = null

    private var controller = object : Home.Controller {
        override fun onShowLoading(showLoading: Boolean) {

        }

        override fun onCountriesDataSuccess(countriesRes: List<Country>, numberOfAttempt: Int) {
            countriesList = countriesRes
        }

        override fun onCountriesDataFail(errorMsg: String, numberOfAttempt: Int) {
            TODO("Not yet implemented")
        }
    }

    var presenter: Home.Presenter = HomePresenterImpl(model = theModel, controller = controller)

    fun assertThatCountryListAreEqualTo(expectedCountryList: List<Country>) {
        Assert.assertEquals(expectedCountryList, countriesList)
    }
}