package com.example.gillian.movieticketsapp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class MainActivity extends ActionBarActivity {

    private static String SERVICE_URL = "http://crappyappy.azurewebsites.net/api/MovieTicket/";
    private static String TAG = "MovieTicketCalculator";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calcPrice(View v) {
        final StringBuilder jsonData = new StringBuilder();
        Thread t = new Thread()
        {
            public void run()														// anonymous class
            {
                try
                {
                    // read item selected on spinner and status of tag checkbox
                    Spinner ticketSpinner = (Spinner) findViewById(R.id.ticketTypeSpinner);
                    CheckBox loyaltyCheckBox = (CheckBox) findViewById(R.id.loyaltyCheckBox);

                    EditText numTicketsET = (EditText) findViewById(R.id.numTicketsEditText);
                    String tickets = numTicketsET.getText().toString().trim();

                    String appendUrl = ticketSpinner.getSelectedItem().toString();
                    if (loyaltyCheckBox.isChecked())
                    {
                        appendUrl += "/true";
                    }
                    else
                    {
                        appendUrl += "/false";
                    }

                    appendUrl += "/" + Integer.parseInt(tickets);

                    URI uri = new URI(SERVICE_URL + appendUrl);						// construct RESTful URL
                    Log.d(TAG, "Connecting to " + uri.toString());

                    // issue a GET to Url
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet get = new HttpGet(uri);

                    HttpResponse response = httpClient.execute(get);

                    // get a reader to response content
                    InputStream is = response.getEntity().getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                    String line = null;
                    try
                    {
                        while ((line = reader.readLine()) != null)					// read line by line
                        {
                            jsonData.append(line + "\n");
                        }
                    }
                    catch (IOException e)
                    {
                        Log.d(TAG, e.toString());
                    }
                    finally
                    {
                        try
                        {
                            is.close();
                        }
                        catch (IOException e)
                        {
                            Log.d(TAG, e.toString());
                        }
                    }
                }
                catch (Exception e)
                {
                    Log.d(TAG, e.toString());
                }
            }
        };

        try
        {
            t.start();						// kick off thread
            t.join();						// wait for thread to finish

            // display a 'toast' message with the result
            Context context = getApplicationContext();

            // strip off " "
            CharSequence text = "$" + jsonData.toString();
            Log.d(TAG, text.toString());

            int duration = Toast.LENGTH_LONG; 		// or LENGTH_SHORT
            Toast.makeText(context, text, duration).show();

        }
        catch (Exception e)
        {
            Log.d(TAG, e.toString());
        }

    }
}