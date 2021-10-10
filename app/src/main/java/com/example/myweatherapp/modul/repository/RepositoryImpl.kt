package com.example.myweatherapp.modul.repository

import com.example.myweatherapp.modul.entities.Weather

class RepositoryImpl : Repository {
    override fun getWeatherFromServer() = Weather()

    override fun getWeatherFromLocalStorage() = Weather()

}