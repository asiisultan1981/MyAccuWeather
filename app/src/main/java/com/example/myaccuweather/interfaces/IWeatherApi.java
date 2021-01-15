package com.example.myaccuweather.interfaces;

import com.example.myaccuweather.models.AccuWeatherModel;
import com.example.myaccuweather.models.LocationSearchModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IWeatherApi {
    @GET("currentconditions/v1/{key}")
    Call<List<AccuWeatherModel>> getAccuWeatherData(
            @Path("key") String cityKey,
            @Query("apikey") String appId);

    @GET("locations/v1/cities/autocomplete")
    Call<List<LocationSearchModel>> getAccuWeatherCities(
            @Query("apikey") String appId,
            @Query("q") String query);
}
