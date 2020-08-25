package com.androidhomework.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidhomework.App
import com.androidhomework.R
import com.androidhomework.api.NetworkApi
import com.androidhomework.databinding.ActivityHomeBinding
import com.androidhomework.dialog.ErrorDialog
import com.androidhomework.dialog.LoadingOverlayDialogController
import com.androidhomework.model.Country
import com.androidhomework.utils.Navigation
import org.koin.android.ext.android.inject


class HomeActivity : AppCompatActivity(), Home.Controller, OnCountryClickListener {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var countryRepo: CountryRepo
    private lateinit var homeModel: HomeModelImpl
    private lateinit var homePresenter: HomePresenterImpl
    private lateinit var countryAdapter: CountryAdapter

    private var loadingDialog: LoadingOverlayDialogController? = null
    private var errorDialog: ErrorDialog? = null
    private var countryList: List<Country> = ArrayList()
    private var isRefresh = false

    private val service: NetworkApi by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.swipeRefreshLayout.isRefreshing = false
        binding.swipeRefreshLayout.setOnRefreshListener {
            isRefresh = true
            homePresenter.initialize()
        }

        loadingDialog = LoadingOverlayDialogController(this@HomeActivity, Runnable { })

        countryRepo = CountryRepoImpl(database = App.instance.roomDatabase, service = service)

        homeModel = HomeModelImpl(countryRepo = countryRepo)

        homePresenter = HomePresenterImpl(model = homeModel, controller = this@HomeActivity)

        countryAdapter = CountryAdapter(this)
        countryAdapter.setOnCountryClickListener(this)
        binding.recyclerView.adapter = countryAdapter
    }

    override fun onResume() {
        super.onResume()
        homePresenter.initialize()
    }

    override fun onCountriesDataSuccess(countriesRes: List<Country>, numberOfAttempt: Int) {
        countryList = countriesRes
        if (countryList.isNotEmpty()) {
            countryAdapter.setCountriesData(countryList)
            binding.swipeRefreshLayout.isRefreshing = isRefresh
            onShowLoading(false)
        } else {
            if (countryList.isEmpty() && numberOfAttempt == 1) {
                onShowLoading(true)
            } else {
                onShowLoading(false)
            }
        }
    }

    override fun onCountriesDataFail(errorMsg: String, numberOfAttempt: Int) {
        onShowLoading(false)
        errorDialog = ErrorDialog(this, getString(R.string.errorDialogTitle), errorMsg, Runnable { })
        errorDialog?.show()
    }

    override fun onShowLoading(showLoading: Boolean) {
        if (showLoading) {
            isRefresh = false
            loadingDialog?.show()
        } else {
            isRefresh = false
            loadingDialog?.dismiss()
        }
    }

    override fun onCountryClicked(country: Country) {
        Navigation.navigateToCountryDetailActivity(this, country)
    }
}