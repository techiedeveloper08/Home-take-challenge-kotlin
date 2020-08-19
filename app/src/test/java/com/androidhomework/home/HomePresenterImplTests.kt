package com.androidhomework.home

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.androidhomework.TestCountry
import com.androidhomework.model.Country
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomePresenterImplTests {

    private lateinit var controller: HomePresenterTestController

    private val gson = Gson()

    private val baseCountryList: List<Country>
        get() {
            return gson.fromJson(TestCountry.countries, object : TypeToken<List<Country>>() {}.type)
        }

    @Before
    fun setUp() {
        controller = HomePresenterTestController()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun `given not initialized when initialize then show correct country list`() {

        val expectedCountryList = baseCountryList

        controller.apply {
            presenter.initialize()
        }

        controller.assertThatCountryListAreEqualTo(
                expectedCountryList
        )
    }
}