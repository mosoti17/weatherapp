package com.mosoti.weatherapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;
import okhttp3.Callback;
import okhttp3.Call;


public class WeatherActivity extends AppCompatActivity {
    public static final String TAG =WeatherActivity.class.getSimpleName();
    @Bind(R.id.locationTextView) TextView mLocationTextView;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private WeatherListAdapter mAdapter;

    public ArrayList<Weather> mForecast = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here is the Weather for: " + location);

        getWeather(location);
    }
    public void getWeather(String location) {
        final WeatherService weatherService = new WeatherService();
        weatherService.getWeather(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

//                String jsonData = response.body().string();
//                 Log.v(TAG, jsonData);
                mForecast=weatherService.processResults(response);
                WeatherActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        mAdapter = new WeatherListAdapter(getApplicationContext(), mForecast);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(WeatherActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        for (Weather weather : mForecast) {
                            Log.d(TAG, "Name: " + weather.getmDate());
                            Log.d(TAG, "Teemp: " + weather.getmTemp());
                            Log.d(TAG, "image: " + weather.getmImage());
                            Log.d(TAG, "description: " + weather.getmDescription());
                            Log.d(TAG, "humidity: " + weather.getmHumidity());

                        }

                    }

                });
            }

        });
    }


}