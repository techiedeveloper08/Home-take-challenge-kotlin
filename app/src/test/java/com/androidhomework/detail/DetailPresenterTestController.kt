package com.androidhomework.detail

import com.androidhomework.TestCountryDetailRepo
import com.androidhomework.model.Country
import com.androidhomework.model.Notes
import com.androidhomework.ui.countrydetail.CountryDetail
import com.androidhomework.ui.countrydetail.CountryDetailModelImpl
import com.androidhomework.ui.countrydetail.CountryDetailPresenterImpl
import org.junit.Assert

class DetailPresenterTestController {

    private val countryDetailRepo: TestCountryDetailRepo = TestCountryDetailRepo()

    private val theModel: TestDetailModel = TestDetailModel(countryDetailModelImpl = CountryDetailModelImpl(countryDetailRepo = countryDetailRepo))

    private var countryById: Country? = null
    private var notesByCountryId: Notes? = null

    private var controller = object : CountryDetail.Controller {

        override fun onCountryDataSuccess(country: Country) {
            countryById = country
        }

        override fun onCountryDataFail(errorMsg: String) {

        }

        override fun onNotesDataSuccess(notes: Notes) {
            notesByCountryId = notes
        }

    }

    var presenter: CountryDetail.Presenter = CountryDetailPresenterImpl(model = theModel, controller = controller)

    fun assertThatCountryAreEqualTo(expectedCountry: Country) {
        Assert.assertEquals(expectedCountry, countryById)
    }

    fun assertThatNotesAreEqualTo(expectedNotes: Notes) {
        Assert.assertEquals(expectedNotes, notesByCountryId)
    }

    fun assertThatNotesAreNotEqualTo(expectedNotes: Notes) {
        Assert.assertNotEquals(expectedNotes, notesByCountryId)
    }
}