package com.example.myweatherapp.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myweatherapp.R
import com.example.myweatherapp.databinding.MainFragmentBinding
import com.example.myweatherapp.modul.entities.Weather
import com.example.myweatherapp.ui.AppState
import com.example.myweatherapp.ui.adapters.MainFragmentAdapter
import com.example.myweatherapp.ui.details.DetailsFragment
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


private const val dataSetKey = "dataSetKey"

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModel()

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private var adapter: MainFragmentAdapter? = null
    private var isDataSetRus: Boolean = true


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            mainFragmentRecyclerView.adapter = adapter
            mainFragmentFAB.setOnClickListener { changeWeatherDataSet() }
            viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
            viewModel.getWeatherFromLocalSourceRus()
        }


        loadDataSet()
        initDataSet()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun changeWeatherDataSet() = with(binding) {
        isDataSetRus = !isDataSetRus
        initDataSet()
    }

    private fun loadDataSet() {
        activity?.let {
            isDataSetRus = activity
                ?.getPreferences(Context.MODE_PRIVATE)
                ?.getBoolean(dataSetKey, true) ?: true
        }
    }

    private fun initDataSet() = with(binding) {
        if (isDataSetRus) {
            viewModel.getWeatherFromLocalSourceWorld()
            mainFragmentFAB.setImageResource(R.drawable.ic_earth)
        } else {
            viewModel.getWeatherFromLocalSourceRus()
            mainFragmentFAB.setImageResource(R.drawable.ic_russia)
        }
        saveDataSetToDisk()
    }

    private fun saveDataSetToDisk() {
        val editor = activity?.getPreferences(Context.MODE_PRIVATE)?.edit()
        editor?.putBoolean(dataSetKey, isDataSetRus)
        editor?.apply()
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                mainFragmentLoadingLayout.visibility = View.GONE
                adapter = MainFragmentAdapter(object : OnItemViewClickListener {
                    override fun onItemViewClick(weather: Weather) {
                        val manager = activity?.supportFragmentManager
                        manager?.let { manager ->
                            val bundle = Bundle().apply {
                                putParcelable(DetailsFragment.BUNDLE_EXTRA, weather)
                            }
                            manager.beginTransaction()
                                .add(R.id.container, DetailsFragment.newInstance(bundle))
                                .addToBackStack("")
                                .commitAllowingStateLoss()
                        }
                    }
                }).apply {
                    setWeather(appState.weatherData)
                }
                mainFragmentRecyclerView.adapter = adapter
            }
            is AppState.Loading -> {
                mainFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                mainFragmentLoadingLayout.visibility = View.GONE
                Snackbar
                    .make(
                        binding.mainFragmentFAB,
                        getString(R.string.error),
                        Snackbar.LENGTH_INDEFINITE
                    )
                    .setAction(getString(R.string.reload)) { viewModel.getWeatherFromLocalSourceRus() }
                    .show()
            }
        }
    }


    interface OnItemViewClickListener {
        fun onItemViewClick(weather: Weather)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}