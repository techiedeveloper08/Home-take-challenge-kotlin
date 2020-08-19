package com.androidhomework

import android.app.Application
import com.androidhomework.database.AppDatabase
import com.androidhomework.database.RoomDatabase
import com.androidhomework.koin.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.util.*

class App : Application() {

    init {
        instance = this
    }

    lateinit var roomDatabase: AppDatabase

    companion object {
        lateinit var instance: App
        var uuid = UUID.randomUUID().toString()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        roomDatabase = RoomDatabase.getInstance(applicationContext)

        val listOfModule = arrayListOf(networkModule)

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOfModule)
        }
    }
}