package com.androidhomework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androidhomework.model.Country

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(order: List<Country>)

    @Query("DELETE FROM country")
    fun deleteTable()

    @Query("SELECT * FROM country")
    fun loadCountries(): List<Country>

    @Query("SELECT * FROM country where id =:id")
    fun getCountryById(id: String): Country
}