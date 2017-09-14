package com.mosoti.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG =MainActivity.class.getSimpleName();
    @Bind(R.id.findWeatherButton) Button mWeatherButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mWeatherButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == mWeatherButton) {
            String location = mLocationEditText.getText().toString();
            Log.d(TAG, "city"+location);
            Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }
    }
}
