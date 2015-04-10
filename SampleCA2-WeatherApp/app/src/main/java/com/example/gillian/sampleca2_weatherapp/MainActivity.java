package com.example.gillian.sampleca2_weatherapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements OnItemSelectedListener {

    private ArrayList<WeatherInfo> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<WeatherInfo>();
        data.add(new WeatherInfo("Cork", 8, WeatherConditions.Sunny));
        data.add(new WeatherInfo("Dublin", 9, WeatherConditions.Sunny));
        data.add(new WeatherInfo("Galway", 10, WeatherConditions.Cloudy));
        data.add(new WeatherInfo("Limerick", 11, WeatherConditions.Rain));

        Spinner spinner = (Spinner) findViewById(R.id.citySpinner);

        List<String> cities = new ArrayList<String>();
        for (int i = 0; i < data.size(); i++){
            cities.add(data.get(i).getCity());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String city = parent.getItemAtPosition(pos).toString();
        TextView temperatureV = (TextView) findViewById(R.id.temperatureTextView);
        ImageView conditionsV = (ImageView) findViewById(R.id.conditionsImageView);

        for(WeatherInfo w: data){
            if(w.getCity().equalsIgnoreCase(city)) {
                temperatureV.setText(w.getTemperature() + " Celsius" );
                if (w.getCondidtions() == WeatherConditions.Sunny){
                    conditionsV.setImageResource(R.drawable.sunny);
                }
                else if (w.getCondidtions() == WeatherConditions.Cloudy){
                    conditionsV.setImageResource(R.drawable.cloudy);
                }
                else if (w.getCondidtions() == WeatherConditions.Rain){
                    conditionsV.setImageResource(R.drawable.rain);
                }
            }
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
