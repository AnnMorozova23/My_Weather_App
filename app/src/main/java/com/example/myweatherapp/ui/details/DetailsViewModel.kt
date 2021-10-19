package com.example.myweatherapp.ui.details

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweatherapp.modul.repository.Repository
import com.example.myweatherapp.ui.AppState


class DetailsViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {

    val liveDataToObserver: MutableLiveData<AppState> = MutableLiveData()

    fun loadData(lat: Double, lon: Double) {
        liveDataToObserver.value = AppState.Loading
        Thread {
            val data = repository.getWeatherFromServer(lat, lon)
            liveDataToObserver.postValue(AppState.Success(listOf(data)))
        }.start()
    }
}