package com.example.myaccuweather.interfaces;

public interface IWeatherCallbackListener <T>{
    <Y>void getWeatherData(Y weatherModel, Boolean success, String errorMsg);
}
