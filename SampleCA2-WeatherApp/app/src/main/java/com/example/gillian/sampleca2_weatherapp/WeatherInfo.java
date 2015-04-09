package com.example.gillian.sampleca2_weatherapp;

/**
 * Created by Gillian on 09/04/2015.
 */

enum WeatherConditions{
    Cloudy, Sunny, Rain
}

public class WeatherInfo {

    private String city;
    public String getCity(){
        return city;
    };

    private double temperature;
    public double getTemperature() {
        return temperature;
    };

    private WeatherConditions condidtions;
    public WeatherConditions getCondidtions(){
      return condidtions;
    };

    public WeatherInfo(String city, double temperature, WeatherConditions condidtions){
        this.city = city;
        this.temperature = temperature;
        this.condidtions = condidtions;
    }

}
