package com.androidhomework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androidhomework.model.Notes

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(notes: Notes)

    @Query("SELECT * FROM notes where countryId =:countryId")
    fun getNotesByCountryId(countryId: String): Notes?

    @Query("UPDATE notes SET notes =:notes where countryId = :countryId")
    fun updateNotes(countryId: String, notes: String?)
}