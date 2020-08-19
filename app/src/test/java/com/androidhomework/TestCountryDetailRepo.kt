package com.androidhomework

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.androidhomework.model.Country
import com.androidhomework.model.Notes
import com.androidhomework.ui.countrydetail.CountryDetailRepo
import io.reactivex.Observable

class TestCountryDetailRepo: CountryDetailRepo {

    private val gson = Gson()

    private val countryList: List<Country>
        get() {
            return gson.fromJson(TestCountry.countries, object : TypeToken<List<Country>>() {}.type)
        }

    private val notesList: List<Notes>
        get() {
            return gson.fromJson(TestCountry.notes, object : TypeToken<List<Notes>>() {}.type)
        }

    override fun getCountryDataById(countryId: String, onSuccess: (country: Country) -> Unit) {
        Observable.just(countryList)
                .subscribe {
                    onSuccess(it.find { country -> country.id == countryId }!!)
                }
    }

    override fun getNotesDataById(countryId: String, onSuccess: (notes: Notes) -> Unit) {
        Observable.just(notesList)
                .subscribe {
                    onSuccess(it.find { it.countryId  == countryId }!!)
                }
    }

    override fun updateNotes(notes: Notes?) {

    }

    override fun isDisposed(): Boolean {
        TODO("Not yet implemented")
    }

    override fun dispose() {
        TODO("Not yet implemented")
    }
}
