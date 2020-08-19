package com.androidhomework.ui.countrydetail

import com.androidhomework.database.AppDatabase
import com.androidhomework.model.Country
import com.androidhomework.model.Notes
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CountryDetailRepoImpl(private val database: AppDatabase) : CountryDetailRepo {

    private val compositeDisposable = CompositeDisposable()

    override fun getCountryDataById(countryId: String, onSuccess: (country: Country) -> Unit) {
        compositeDisposable.add(
                getCountryFromDBById(countryId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            onSuccess(it)
                        }
        )
    }

    override fun getNotesDataById(countryId: String, onSuccess: (notes: Notes) -> Unit) {
        compositeDisposable.add(
                getNotesFromDBById(countryId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            onSuccess(it)
                        }
        )
    }

    override fun updateNotes(notes: Notes?) {
        notes?.let {
            if (database.notesDao().getNotesByCountryId(it.countryId) != null) {
                database.notesDao().updateNotes(it.countryId, it.notes)
            } else {
                database.notesDao().insert(it)
            }
        }
    }

    private fun getCountryFromDBById(countryId: String): Observable<Country> {
        return Observable.fromCallable {
            database.countryDao().getCountryById(countryId)
        }
    }

    private fun getNotesFromDBById(countryId: String): Observable<Notes> {
        return Observable.fromCallable {
            val notes = database.notesDao().getNotesByCountryId(countryId)
            notes ?: Notes(countryId, "")
        }
    }

    override fun isDisposed(): Boolean {
        return compositeDisposable.isDisposed
    }

    override fun dispose() {
        compositeDisposable.dispose()
    }
}