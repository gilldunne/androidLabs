package com.example.gillian.cartoll;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements OnItemSelectedListener {

    String vehicle;
    private ArrayList<Toll> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<Toll>();
        data.add(new Toll(VehicleType.Car));
        data.add(new Toll(VehicleType.Bus));
        data.add(new Toll(VehicleType.Truck));

        Spinner spinner = (Spinner) findViewById(R.id.vehicleSpinner);
        List<String> vehicles = new ArrayList<String>();
        for(int i = 0; i < data.size(); i++){
            vehicles.add(String.valueOf(data.get(i).getVehicleType()));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, vehicles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        vehicle = parent.getItemAtPosition(pos).toString();
        ImageView vehicleV = (ImageView) findViewById(R.id.vehicleImageView);

        if(vehicle == VehicleType.Truck.toString()){
            vehicleV.setImageResource(R.drawable.truck);
        }
        else if(vehicle == VehicleType.Bus.toString()){
            vehicleV.setImageResource(R.drawable.bus);
        }
        else if(vehicle == VehicleType.Car.toString()){
            vehicleV.setImageResource(R.drawable.car);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void hasTagClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        TextView costTV = (TextView)findViewById(R.id.costTextView);

        for(Toll t: data){
            if(t.getVehicleType().toString() == vehicle)
                if(checked){
                    costTV.setText((t.getCost()*0.80) + " ");
                }
                else{
                    costTV.setText(t.getCost() + " ");
                }
        }
    }

    public void calcCostClicked(View view){
        Context context = getApplicationContext();
        CharSequence text = "Toll Paid!";
        int duration = Toast.LENGTH_SHORT; // or LENGTH_LONG
        Toast.makeText(context, text, duration).show();
    }
}
