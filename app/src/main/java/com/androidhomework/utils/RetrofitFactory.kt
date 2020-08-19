package com.androidhomework.utils

import android.content.Context
import com.androidhomework.BuildConfig
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {

    fun getRetrofit(application: Context, API: String): Retrofit {
        val httpClient = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }

        httpClient.interceptors().add(BaseInterceptor())
        httpClient.interceptors().add(ConnectivityInterceptor(application))
        httpClient.interceptors().add(BasicAuthInterceptor(user = "developer", password = "metide"))
        httpClient.connectTimeout(1, TimeUnit.MINUTES).writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)

        return Retrofit.Builder()
                .baseUrl(API)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }
}