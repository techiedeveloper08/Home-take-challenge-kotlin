package com.androidhomework.utils

import android.location.Location
import com.androidhomework.model.Country
import com.google.android.gms.maps.model.LatLng
import java.util.*

class SortCountry(var currentLoc: LatLng) : Comparator<Country> {

    override fun compare(place1: Country, place2: Country): Int {
        val lat1 = place1.latitude?.toDouble() ?: 0.0
        val lon1 = place1.longitude?.toDouble() ?: 0.0
        val lat2 = place2.latitude?.toDouble() ?: 0.0
        val lon2 = place2.longitude?.toDouble() ?: 0.0

        val result1 = FloatArray(3)
        Location.distanceBetween(currentLoc.latitude, currentLoc.longitude, lat1, lon1, result1)
        val distance1 = result1[0]

        val result2 = FloatArray(3)
        Location.distanceBetween(currentLoc.latitude, currentLoc.longitude, lat2, lon2, result2)
        val distance2 = result2[0]

        return distance1.compareTo(distance2)
    }
}