package com.example.myweatherapp.modul.repository

import com.example.myweatherapp.modul.entities.Weather
import com.example.myweatherapp.modul.entities.getRussianCities
import com.example.myweatherapp.modul.entities.getWorldCities

class RepositoryImpl : Repository {
    override fun getWeatherFromServer() = Weather()

    override fun getWeatherFromLocalStorageRus() = getRussianCities()

    override fun getWeatherFromLocalStorageWorld() = getWorldCities()


}