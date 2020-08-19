package com.androidhomework.detail

import com.androidhomework.model.Country
import com.androidhomework.model.Notes
import com.androidhomework.ui.countrydetail.CountryDetail
import com.androidhomework.ui.countrydetail.CountryDetailModelImpl

class TestDetailModel (private val countryDetailModelImpl: CountryDetailModelImpl) : CountryDetail.Model by countryDetailModelImpl {

    override fun getCountryDataById(countryId: String, onSuccess: (country: Country) -> Unit, onFail: (errorMsg: String) -> Unit) {
        countryDetailModelImpl.getCountryDataById(countryId, onSuccess, onFail)
    }

    override fun getNotesDataById(countryId: String, onSuccess: (notes: Notes) -> Unit) {
        countryDetailModelImpl.getNotesDataById(countryId, onSuccess)
    }

    override fun updateNotes(notes: Notes?) {

    }
}