package com.mosoti.weatherapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mosoti on 9/14/17.
 */

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder> {

    private ArrayList<Weather> mWeather = new ArrayList<>();
    private Context mContext;

    public WeatherListAdapter(Context context, ArrayList<Weather> updates) {
        mContext = context;
        mWeather = updates;
    }

    @Override
    public WeatherListAdapter.WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list, parent, false);
        WeatherViewHolder viewHolder = new WeatherViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherListAdapter.WeatherViewHolder holder, int position) {
        holder.bindWeather(mWeather.get(position));
    }

    @Override
    public int getItemCount() {
        return mWeather.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageView) ImageView mImageView;
        @Bind(R.id.tempView) TextView mTempView;
        @Bind(R.id.humidity) TextView mHumidityView;
        @Bind(R.id.date) TextView mDateView;

        private Context mContext;

        public WeatherViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            Log.v("c","hwdbsfhn");
        }

        public void bindWeather(Weather weather) {
            Picasso.with(mContext).load(weather.getmImage()).into(mImageView);
            mTempView.setText(String.valueOf(weather.getmTemp()));
            mHumidityView.setText(String.valueOf(weather.getmHumidity()));
            mDateView.setText(weather.getmDate());

        }
    }
}
