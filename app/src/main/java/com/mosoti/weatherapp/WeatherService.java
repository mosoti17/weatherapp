package com.mosoti.weatherapp;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mosoti on 9/13/17.
 */

public class WeatherService {

    public static void getWeather(String id, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.API_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.YOUR_QUERY_PARAMETER, id);
        urlBuilder.addQueryParameter(Constants.API_KEY_QUERY_PARAMETER, Constants.API_KEY);
        String url = urlBuilder.build().toString();

        Request request=new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Weather> processResults(Response response) {
        ArrayList<Weather> forecasts = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject openwJSON = new JSONObject(jsonData);
                JSONArray listJSON = openwJSON.getJSONArray("list");

                for (int i = 0; i < listJSON.length(); i++) {
                    JSONObject forecastJSON = listJSON.getJSONObject(i);
                    String date=forecastJSON.getString("dt_txt");
                    double kelvin = forecastJSON
                            .getJSONObject("main").getDouble("temp");
                    int temp = (int) Math.round(kelvin-273.15);

                    String description="";
                    String image ="";

                   JSONArray weatherArray= forecastJSON.getJSONArray("weather");
                    for (int j=0;j<weatherArray.length();j++){
                        JSONObject item= weatherArray.getJSONObject(j);
                        description = item.getString("description");


                        image = "http://openweathermap.org/img/w/"+item.getString("icon")+".png";

                    }


                    Integer humidity =forecastJSON
                            .getJSONObject("main").getInt("humidity");

                    Weather weather = new Weather(date,temp,description,image,humidity);
                    forecasts.add(weather);

                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return forecasts;
    }
}
