package com.example.myweatherapp.modul.repository

import com.example.myweatherapp.modul.entities.Weather

interface Repository {
    fun getWeatherFromServer(lat:Double,lon:Double): Weather
    fun getWeatherFromLocalStorageRus(): List<Weather>
    fun getWeatherFromLocalStorageWorld(): List<Weather>
}