package com.androidhomework.ui.countrydetail

import com.androidhomework.model.Country
import com.androidhomework.model.Notes

class CountryDetailModelImpl(private val countryDetailRepo: CountryDetailRepo) : CountryDetail.Model {

    override fun getCountryDataById(countryId: String, onSuccess: (country: Country) -> Unit, onFail: (errorMsg: String) -> Unit) {
        countryDetailRepo.getCountryDataById(countryId, onSuccess = {
            if (it != null) {
                onSuccess(it)
            } else {
                onFail("Error: Country not found")
            }
        })
    }

    override fun getNotesDataById(countryId: String, onSuccess: (notes: Notes) -> Unit) {
        countryDetailRepo.getNotesDataById(countryId, onSuccess)
    }

    override fun updateNotes(notes: Notes?) {
        countryDetailRepo.updateNotes(notes)
    }
}