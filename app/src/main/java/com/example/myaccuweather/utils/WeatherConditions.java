package com.example.myaccuweather.utils;

import android.util.Log;

import com.example.myaccuweather.interfaces.IWeatherApi;
import com.example.myaccuweather.interfaces.IWeatherCallbackListener;
import com.example.myaccuweather.models.AccuWeatherModel;

import java.nio.file.ClosedFileSystemException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myaccuweather.constants.ProjectConstants.BASE_DEV_URL_ACCU_WEATHER;
import static com.example.myaccuweather.constants.ProjectConstants.BASE_URL_ACCU_WEATHER;

public class WeatherConditions {
    private static final String TAG = "condition";
    private static IWeatherApi mWeatherApi;



    public static void getAccuWeatherData(String city, String appId, IWeatherCallbackListener listener, Boolean isProductionUrl) {

//        if (isProductionUrl)
//            mWeatherApi = ApiService.getRetrofitInstance(BASE_URL_ACCU_WEATHER).create(IWeatherApi.class);
//        else
            mWeatherApi = ApiService.getRetrofitInstance(BASE_DEV_URL_ACCU_WEATHER).create(IWeatherApi.class);
        Call<List<AccuWeatherModel>> call = mWeatherApi.getAccuWeatherData(city, appId);
        call.enqueue(new Callback<List<AccuWeatherModel>>() {
            @Override
            public void onResponse(Call<List<AccuWeatherModel>> call, Response<List<AccuWeatherModel>> response) {
                if (response.body() != null) {
                        if (listener != null)
                        listener.getWeatherData(response.body().get(0), true, "");
                    Log.e(TAG, "onResponse: "+response.body().get(0).toString() );
                }
            }

            @Override
            public void onFailure(Call<List<AccuWeatherModel>> call, Throwable t) {
                listener.getWeatherData(null, false, t.getMessage());
                Log.e(TAG, "onFailure: "+t.getMessage() );
            }
        });
    }
}
