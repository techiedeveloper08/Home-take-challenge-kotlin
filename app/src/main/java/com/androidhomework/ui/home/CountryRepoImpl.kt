package com.androidhomework.ui.home

import com.androidhomework.api.NetworkApi
import com.androidhomework.database.AppDatabase
import com.androidhomework.model.Country
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class CountryRepoImpl(private val database: AppDatabase, private val service: NetworkApi) : CountryRepo {

    private val compositeDisposable = CompositeDisposable()

    override fun getCountriesData(onSuccess: (countryRes: List<Country>, numberOfAttempt: Int) -> Unit, onError:(error: Throwable, numberOfAttempt: Int) -> Unit) {
        var numberOfAttempt = 0
        compositeDisposable.add(
                getCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    numberOfAttempt++
                    onSuccess(it, numberOfAttempt)
                }, {
                    onError(it, numberOfAttempt)
                }))
    }

    private fun getCountries(): Observable<List<Country>> {
        return Observable.concatArrayEager(
                getCountriesFromDB(),
                getCountriesFromServer()
                        .doOnNext { response ->
                            response.body()?.let {
                                database.countryDao().deleteTable()
                                database.countryDao().insertAll(it)
                            }
                        }
                        .flatMap {
                            getCountriesFromDB()
                        }
                        .subscribeOn(Schedulers.io()))
    }

    private fun getCountriesFromServer(): Observable<Response<List<Country>>> {
        return service.getCountries()
    }

    private fun getCountriesFromDB(): Observable<List<Country>> {
        return Observable.fromCallable {
            database.countryDao().loadCountries()
        }
    }

    override fun isDisposed(): Boolean {
        return compositeDisposable.isDisposed
    }

    override fun dispose() {
        compositeDisposable.dispose()
    }
}