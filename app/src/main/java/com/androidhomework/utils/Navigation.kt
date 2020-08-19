package com.androidhomework.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.androidhomework.model.Country
import com.androidhomework.ui.countrydetail.CountryDetailActivity
import com.androidhomework.ui.home.HomeActivity

object Navigation {

    object IntentFactory {

        @JvmStatic
        val activityClearTopFlag: Intent.() -> Unit = {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        @JvmStatic
        fun countryDetailActivityIntent(context: Context, modifier: Intent.() -> Unit = {}) =
            Intent(context, CountryDetailActivity::class.java).apply(modifier)


        @JvmStatic
        fun homeActivityIntent(
                context: Context,
                vararg modifiers: Intent.() -> Unit = arrayOf()
        ) =
                Intent(
                        context,
                        HomeActivity::class.java
                ).apply { modifiers.forEach { this.apply(it) } }

    }

    @JvmStatic
    fun navigateToHomeActivity(activity: Activity): Intent {
        return IntentFactory.homeActivityIntent(
                activity,
                IntentFactory.activityClearTopFlag).also {
                activity.startActivity(it)
                activity.finish()
            }
    }

    @JvmStatic
    fun navigateToCountryDetailActivity(activity: Activity, country: Country) {
        IntentFactory.countryDetailActivityIntent(activity) {

            putExtra(CountryDetailActivity.ARG_COUNTRY, country)

        }.also(activity::startActivity)
    }
}