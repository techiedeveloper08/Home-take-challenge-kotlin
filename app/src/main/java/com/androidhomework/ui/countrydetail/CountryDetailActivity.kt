package com.androidhomework.ui.countrydetail

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidhomework.App
import com.bumptech.glide.Glide
import com.androidhomework.R
import com.androidhomework.databinding.ActivityCountryDetailBinding
import com.androidhomework.dialog.ErrorDialog
import com.androidhomework.model.Country
import com.androidhomework.model.Notes
import com.androidhomework.utils.memoized

class CountryDetailActivity : AppCompatActivity(), CountryDetail.Controller {

    companion object {
        const val ARG_COUNTRY = "argCountry"
    }

    private lateinit var binding: ActivityCountryDetailBinding

    private var errorDialog: ErrorDialog? = null
    private lateinit var countryDetailRepo: CountryDetailRepo
    private lateinit var model: CountryDetail.Model
    private lateinit var presenter: CountryDetail.Presenter
    private var notes: Notes? = null

    private val country: Country? by memoized {
        intent?.getParcelableExtra(ARG_COUNTRY) as Country?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        countryDetailRepo = CountryDetailRepoImpl(App.instance.roomDatabase)
        model = CountryDetailModelImpl(countryDetailRepo)
        presenter = CountryDetailPresenterImpl(model = model, controller = this@CountryDetailActivity)

        binding.btnSubmit.setOnClickListener {
            if (binding.etNote.text.toString().trim().isNotBlank()) {
                notes?.notes = binding.etNote.text.toString()
                presenter.updateNotes(notes)
                Toast.makeText(this, "Notes Saved!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Notes must not be empty!", Toast.LENGTH_SHORT).show()
            }
        }

        country?.let {
            presenter.initialize(it.id)
        }
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(menuItem)
    }

    override fun onCountryDataSuccess(country: Country) {
        country.run {
            binding.countryName.text = name
            notes?.let { binding.etNote.setText(it.notes) }
            Glide.with(this@CountryDetailActivity)
                    .load(flag)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round)
                    .into(binding.countryFlag)
        }
    }

    override fun onNotesDataSuccess(notes: Notes) {
        this.notes = notes
        notes.notes?.let { binding.etNote.setText(it) }
    }

    override fun onCountryDataFail(errorMsg: String) {
        errorDialog = ErrorDialog(this, getString(R.string.errorDialogTitle), errorMsg, Runnable { })
        errorDialog?.show()
    }
}