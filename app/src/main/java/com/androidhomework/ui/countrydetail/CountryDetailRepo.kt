package com.androidhomework.ui.countrydetail

import com.androidhomework.model.Country
import com.androidhomework.model.Notes
import io.reactivex.disposables.Disposable

interface CountryDetailRepo : Disposable {
    fun getCountryDataById(countryId: String, onSuccess: (country: Country) -> Unit)
    fun getNotesDataById(countryId: String, onSuccess: (notes: Notes) -> Unit)
    fun updateNotes(notes: Notes?)
}