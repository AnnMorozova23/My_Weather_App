package com.example.myweatherapp.modul.repository

import com.example.myweatherapp.modul.entities.Weather

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorage(): Weather
}