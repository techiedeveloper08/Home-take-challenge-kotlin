package com.androidhomework.ui.home

import com.google.android.gms.maps.model.LatLng
import com.androidhomework.model.Country
import com.androidhomework.utils.SortCountry
import java.util.*

class HomeModelImpl(private val countryRepo: CountryRepo) : Home.Model {

    override fun getCountriesData(onSuccess: (countriesRes: List<Country>, numberOfAttempt: Int) -> Unit, onFail: (errorMsg: String, numberOfAttempt: Int) -> Unit) {
        countryRepo.getCountriesData(onSuccess = { countriesData, numberOfAttempt ->
            //Todo Below latLong are Metide headquarters  Ex. Alessandro Volta, 30020 Marcon-Gaggio-Colmello VE, Italy
            val latLong = LatLng(45.5546463, 12.2333877)
            Collections.sort(countriesData, SortCountry(latLong))
            onSuccess(countriesData, numberOfAttempt)
        }, onError = { error , numberOfAttempt ->
            onFail(error.toString(), numberOfAttempt)
        })
    }
}