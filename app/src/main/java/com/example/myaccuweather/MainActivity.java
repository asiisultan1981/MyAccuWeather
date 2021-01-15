package com.example.myaccuweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.bumptech.glide.Glide;
import com.example.myaccuweather.adapters.AutoCompleteAdapter;
import com.example.myaccuweather.databinding.ActivityMainBinding;
import com.example.myaccuweather.interfaces.IWeatherCallbackListener;
import com.example.myaccuweather.models.AccuWeatherModel;
import com.example.myaccuweather.models.LocationSearchModel;
import com.example.myaccuweather.utils.WeatherConditions;

import static com.example.myaccuweather.constants.ProjectConstants.APP_KEY;

public class MainActivity extends AppCompatActivity implements IWeatherCallbackListener {
    private static final String TAG = "main" ;
    ActivityMainBinding binding;
    LocationSearchModel locationSearchModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.etCityName.setThreshold(2);
        binding.etCityName.setAdapter(new AutoCompleteAdapter(MainActivity.this, APP_KEY));

        binding.btnGetWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: Get Weather Data" );

                String city = binding.etCityName.getText().toString();
                WeatherConditions.getAccuWeatherData(locationSearchModel.getKey(), APP_KEY,
                        MainActivity.this, true);
                Log.e(TAG, "afterClicked: Location = "+ city );


            }
        });
        binding.etCityName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                locationSearchModel = (LocationSearchModel) parent.getAdapter().getItem(position);

//                binding.etCityName.setText(locationSearchModel.getKey());
                binding.etCityName.setText(locationSearchModel.getLocalizedName());

                WeatherConditions.getAccuWeatherData(locationSearchModel.getKey(), APP_KEY,
                        MainActivity.this, true);
            }
        });
    }

    @Override
    public void getWeatherData(Object weatherModel, Boolean success, String errorMsg) {
        if (success){
            AccuWeatherModel accuWeatherModel = (AccuWeatherModel) weatherModel;
            binding.tvInfo.setText("Temperature: " + String.valueOf(accuWeatherModel.getTemperature().getMetric().getValue())
            +""+accuWeatherModel.getTemperature().getMetric().getUnit());
            binding.tvCity.setText("City - " + locationSearchModel.getLocalizedName());
            binding.tvCountry.setText("Country - " + locationSearchModel.getCountry().getLocalizedName());
            String imgUrl = "http://apidev.accuweather.com/developers/Media/Default/WeatherIcons/";
            Glide.with(MainActivity.this)
                    .load( imgUrl+ String.format("%02d", accuWeatherModel.getWeatherIcon()) + "-s" + ".png")
                    .into(binding.ivWeatherIcon);
            Log.e(TAG, "getWeatherDataImage: "+ accuWeatherModel.getWeatherIcon());
        }

    }
}