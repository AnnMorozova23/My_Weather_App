package com.example.myweatherapp.ui

import com.example.myweatherapp.modul.entities.Weather

sealed class AppState {
    data class Success(val weatherData: List<Weather>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}