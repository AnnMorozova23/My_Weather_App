package com.example.myweatherapp.modul.repository

import com.example.myweatherapp.modul.entities.Weather
import com.example.myweatherapp.modul.entities.getRussianCities
import com.example.myweatherapp.modul.entities.getWorldCities
import com.example.myweatherapp.modul.rest_entities.WeatherLoader

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(lat: Double, lon: Double): Weather {
        val dto = WeatherLoader.loaderWeather(lat, lon)
        return Weather(
            temperature = dto?.fact?.temp ?: 0,
            feelsLike = dto?.fact?.feels_like ?: 0,
            condition = dto?.fact?.conditions
        )
    }

    override fun getWeatherFromLocalStorageRus() = getRussianCities()

    override fun getWeatherFromLocalStorageWorld() = getWorldCities()


}