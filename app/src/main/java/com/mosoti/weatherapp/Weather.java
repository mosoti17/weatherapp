package com.mosoti.weatherapp;

/**
 * Created by mosoti on 9/13/17.
 */

public class Weather {
    private String mDate;

    private String mDescription;
    private String mImage;
    private int mTemp;
    private Integer mHumidity;

    public Weather(String date,int temp,String description, String image,Integer humidity){
       this.mDate=date;
        this.mTemp=temp;
        this.mHumidity=humidity;
        this.mDescription=description;
        this.mImage=image;


    }
    public String getmDate(){
        return mDate;
    }
    public String getmDescription(){
        return mDescription;
    }
    public double getmTemp(){
        return mTemp;
    }
    public Integer getmHumidity(){
        return mHumidity;
    }
    public String getmImage(){
        return mImage;
    }


}
