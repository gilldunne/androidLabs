package com.example.m50tollcalculator;

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
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.view.Menu;

public class MainActivity extends Activity 
{
	// URL for RESTful service which returns JSON
	// use ASP.Net development server rather than IIS express
	//private static String SERVICE_URL = "http://10.0.2.2:3299/api/M50Toll/";			// 10.0.2.2 needed for emulator
	private static String SERVICE_URL = "http://crappyappy.azurewebsites.net/api/M50Toll/";
	
	// for LogCat
	private static String TAG = "M50TollCalculator";

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    // handler for calcToll button
 	public void calcToll(View v)
 	{
 		
 		final StringBuilder jsonData = new StringBuilder();

        // kick off a new thread which calls RESTful service
        // note: all networking calls in Android 3.0 must be in a separate thread from main thread
        // otherwise exception is thrown
 		// also need Internet permission specified in manifest
 		Thread t = new Thread()
 		{ 
 			public void run()														// anonymous class
 			{
 				try
 				{
 					// read item selected on spinner and status of tag checkbox
 			 		Spinner vehicleSpinner = (Spinner) findViewById(R.id.vehicleTypeSpinner);
 			 		CheckBox tagCheckBox = (CheckBox) findViewById(R.id.tagCheckBox);
 			 		
 			 		String appendUrl = vehicleSpinner.getSelectedItem().toString();
 			 		if (tagCheckBox.isChecked())
 			 		{
 			 			appendUrl += "/true";
 			 		}
 			 		else
 			 		{
 			 			appendUrl += "/false";
 			 		}
 					
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
 			CharSequence text = "� " + jsonData.toString();
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
