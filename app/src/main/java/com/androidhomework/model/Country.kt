package com.androidhomework.model


import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "country")
@Parcelize
data class Country(

    @PrimaryKey
    val id: String,

    val enabled: String?,

    val code3l: String?,

    val code2l: String?,

    val name: String?,

    @SerializedName("name_official")
    val nameOfficial: String?,

    val flag: String?,

    val latitude: String?,

    val longitude: String?,

    val zoom: String?,

    @Embedded(prefix = "notes_")
    var notes: Notes?
): Parcelable