package com.example.myweatherapp.framework.main

import android.util.Log
import androidx.lifecycle.*
import com.example.myweatherapp.AppState
import com.example.myweatherapp.modul.repository.Repository
import java.lang.Thread.sleep

class MainViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {

    private val liveData = MutableLiveData<AppState>()

    fun getLiveData(): LiveData<AppState> = liveData

    fun getWeather() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveData.value = AppState.Loading
        Thread {
            sleep(1000)
            liveData.postValue(AppState.Success(repository.getWeatherFromLocalStorage()))
        }.start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onViewStart() {
        Log.i("LifecycleEvent", "onStart")
    }

}