package com.androidhomework.ui.countrydetail

import com.androidhomework.model.Country
import com.androidhomework.model.Notes

interface CountryDetail {

    interface Model {
        fun getCountryDataById(countryId: String, onSuccess: (country: Country) -> Unit, onFail: (errorMsg: String) -> Unit)
        fun getNotesDataById(countryId: String, onSuccess: (notes: Notes) -> Unit)
        fun updateNotes(notes: Notes?)
    }

    interface Controller {
        fun onCountryDataSuccess(country: Country)
        fun onCountryDataFail(errorMsg: String)
        fun onNotesDataSuccess(notes: Notes)
    }

    interface Presenter {
        fun initialize(countryId: String)
        fun updateNotes(notes: Notes?)
    }
}