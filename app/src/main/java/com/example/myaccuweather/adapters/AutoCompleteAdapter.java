package com.example.myaccuweather.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.myaccuweather.R;
import com.example.myaccuweather.interfaces.IWeatherApi;
import com.example.myaccuweather.models.LocationSearchModel;
import com.example.myaccuweather.utils.ApiService;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myaccuweather.constants.ProjectConstants.BASE_DEV_URL_ACCU_WEATHER;

/**
 * Created by akash on 25/10/17.
 */

public class AutoCompleteAdapter extends BaseAdapter implements Filterable {

    private static final String TAG = "location";
    private List<LocationSearchModel> mResultList = new ArrayList<>();
    private String BASE_URL_ACCU_WEATHER = "https://api.accuweather.com/";
    private Context mContext;
    private String ACCU_WEATHER_APP_ID;

    public AutoCompleteAdapter(Context mContext, String appId) {
        this.mContext = mContext;
        ACCU_WEATHER_APP_ID =appId;
    }

    @Override
    public int getCount() {
        return mResultList.size();
    }

    @Override
    public Object getItem(int i) {
        return mResultList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (inflater != null) {
                view = inflater.inflate(R.layout.row_suggestions, viewGroup, false);
            }
        }

        if (view != null) {
            ((TextView) view.findViewById(R.id.tv_city_suggestion)).setText(mResultList.get(i).getLocalizedName());
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if (charSequence != null) {
                    IWeatherApi weatherApi = ApiService.getRetrofitInstance(BASE_DEV_URL_ACCU_WEATHER).create(IWeatherApi.class);
                    Call<List<LocationSearchModel>> call = weatherApi.getAccuWeatherCities(ACCU_WEATHER_APP_ID,charSequence.toString());
                    call.enqueue(new Callback<List<LocationSearchModel>>() {
                        @Override
                        public void onResponse(Call<List<LocationSearchModel>> call, Response<List<LocationSearchModel>> response) {
                            if (response.body() != null) {
                                if (!response.body().isEmpty()) {
                                    mResultList.clear();
                                    mResultList.addAll(response.body());
                                    filterResults.values = mResultList;
                                    filterResults.count = mResultList.size();
                                    publishResults(charSequence, filterResults);
                                    Log.e(TAG, "onResponse: "+response.toString() );
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<List<LocationSearchModel>> call, Throwable t) {
                            mResultList.clear();
                            publishResults(charSequence, filterResults);
                            Log.e(TAG, "onFailure: "+t.getMessage() );
                        }
                    });
                }
                filterResults.values = mResultList;
                filterResults.count = mResultList.size();
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if (filterResults != null && filterResults.count > 0) {
                    notifyDataSetChanged();
                } else
                    notifyDataSetInvalidated();
            }
        };
        return filter;
    }
}
