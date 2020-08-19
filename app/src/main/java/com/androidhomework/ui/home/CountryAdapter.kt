package com.androidhomework.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.androidhomework.R
import com.androidhomework.model.Country
import com.androidhomework.databinding.CountryItemBinding

class CountryAdapter(private val context: Context) :
        RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    private var countryList: List<Country> = ArrayList()
    private var onCountryClickListener: OnCountryClickListener? = null

    fun setOnCountryClickListener(onCountryClickListener: OnCountryClickListener) {
        this.onCountryClickListener = onCountryClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val countryItemBinding = CountryItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(countryItemBinding)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun setCountriesData(countryList: List<Country>) {
        this.countryList = countryList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(country = countryList[position])
    }

    inner class ViewHolder(private val countryItemBinding: CountryItemBinding) : RecyclerView.ViewHolder(countryItemBinding.root) {

        fun bind(country: Country) {

            countryItemBinding.countryName.text = country.name

            Glide.with(context)
                    .load(country.flag)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round)
                    .into(countryItemBinding.countryFlag)

            countryItemBinding.root.setOnClickListener {
                onCountryClickListener?.onCountryClicked(country)
            }
        }
    }
}

interface OnCountryClickListener {
    fun onCountryClicked(country: Country)
}