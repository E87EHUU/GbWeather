package com.esfimus.gbweather.data.api

import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val WEATHER_API_KEY= "409b3614-e3ba-4f56-8ea3-ca9294962745"

class LoadRetrofitWeather {

    private val weatherApi = Retrofit.Builder()
        .baseUrl("https://api.weather.yandex.ru/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
        .create(RetrofitWeatherApi::class.java)

    fun getWeather(lat: Double, lon: Double, callback: Callback<WeatherLoaded>) {
        weatherApi.getWeather(WEATHER_API_KEY, lat, lon).enqueue(callback)
    }

}