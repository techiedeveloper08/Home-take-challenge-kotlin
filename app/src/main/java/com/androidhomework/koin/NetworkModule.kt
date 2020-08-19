package com.androidhomework.koin

import android.content.Context
import com.androidhomework.BuildConfig
import com.androidhomework.api.NetworkApi
import com.androidhomework.utils.RetrofitFactory
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single { provideRetrofit(get()) }
    single { provideService(get()) }
}

private fun provideRetrofit(context: Context) = RetrofitFactory.getRetrofit(context, BuildConfig.SERVICE_ENDPOINT)

fun provideService(retrofit: Retrofit): NetworkApi {
    return retrofit.create(NetworkApi::class.java)
}