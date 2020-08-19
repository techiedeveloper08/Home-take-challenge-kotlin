package com.androidhomework.ui.countrydetail

import com.androidhomework.model.Country
import com.androidhomework.model.Notes

class CountryDetailPresenterImpl(
        private val model: CountryDetail.Model,
        private val controller: CountryDetail.Controller)
    : CountryDetail.Presenter {

    override fun initialize(countryId: String) {
        model.getCountryDataById(countryId = countryId, onSuccess = ::onCountryDataSuccess, onFail = ::onCountryDataFail)
        model.getNotesDataById(countryId = countryId, onSuccess = ::onNotesDataSuccess)
    }

    override fun updateNotes(notes: Notes?) {
        model.updateNotes(notes)
    }

    private fun onNotesDataSuccess(notes: Notes) {
        controller.onNotesDataSuccess(notes)
    }

    private fun onCountryDataSuccess(country: Country) {
        controller.onCountryDataSuccess(country)
    }

    private fun onCountryDataFail(errorMsg: String) {
        controller.onCountryDataFail(errorMsg)
    }
}