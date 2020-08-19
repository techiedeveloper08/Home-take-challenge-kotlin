package com.androidhomework.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "notes")
@Parcelize
data class Notes (
        @PrimaryKey
        val countryId: String,

        var notes: String?
): Parcelable