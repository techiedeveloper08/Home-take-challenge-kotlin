package com.androidhomework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.androidhomework.model.Country
import com.androidhomework.model.Notes

@Database(
    entities = [Country::class, Notes::class],
    version = 6,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
    abstract fun notesDao(): NotesDao
}