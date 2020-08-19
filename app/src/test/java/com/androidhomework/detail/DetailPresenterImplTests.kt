package com.androidhomework.detail

import com.androidhomework.model.Country
import com.androidhomework.model.Notes
import org.junit.After
import org.junit.Before
import org.junit.Test

class DetailPresenterImplTests {

    private lateinit var controller: DetailPresenterTestController

    private val countryId = "1"

    private val expectedCountry = Country("1", "1", "AFG", "AF", "Afghanistan", "Islamic Republic of Afghanistan", "https://firebasestorage.googleapis.com/v0/b/job-interview-cfe5a.appspot.com/o/AF.png?alt=media", "33.98299275", "66.39159363", "6")
    private val expectedNotes = Notes("1", "Testing Notes")

    @Before
    fun setUp() {
        controller = DetailPresenterTestController()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun `given not initialized when initialize then show correct country by Id`() {

        controller.apply {
            presenter.initialize(countryId)
        }

        controller.assertThatCountryAreEqualTo(expectedCountry)
    }

    @Test
    fun `given not initialized when initialize then show correct notes by Id`() {

        controller.apply {
            presenter.initialize(countryId)
        }

        controller.assertThatNotesAreEqualTo(expectedNotes)
    }

    @Test
    fun `given not initialized when initialize then show correct notes by Id with different text`() {

        val expectedNotes = expectedNotes.copy(notes = "This is Different Notes")

        controller.apply {
            presenter.initialize(countryId)
        }

        controller.assertThatNotesAreNotEqualTo(expectedNotes)
    }
}