// Gillian Dunne X00094469
package com.example.gillian.gilliandunneca;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class MainActivity extends ActionBarActivity {


    String numOfNums;
    String maxNums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void generateNums(View view){
        try{
//            Get the number of numbers for the lotto draw
            EditText numOfNumsET = (EditText) findViewById(R.id.numOfNumbersEditText);
            numOfNums = numOfNumsET.getText().toString().trim();
//            Get the maximum numbers in that draw
            EditText maxNumsET = (EditText) findViewById(R.id.maxNumbersEditText);
            maxNums = maxNumsET.getText().toString().trim();
//            Check the numbers are valid
            errorCheck(view);
//            Create an arraylist to hold all the possible number in the lotto
//            Fill this array list with the numbers in order 1 to maximum amount
            ArrayList<Integer> lottoNum = new ArrayList<Integer>();
            for(int i = 0; i < Integer.parseInt(maxNums); i++){
                lottoNum.add(i+1);
            }
//            Shuffle the arraylist
            Collections.shuffle(lottoNum);
//            Create a second arraylist to hold the generated numbers
//            Fill this arrat with the numbers
            ArrayList<Integer> printNum = new ArrayList<Integer>();
            for(int i = 0; i < Integer.parseInt(numOfNums); i++){
                printNum.add(lottoNum.get(i));
            }
//            Sort the generated numbers into ascending order
//            Add them to a string to be pronted to the screen
            Collections.sort(printNum);
            String text = " ";
            for(int i = 0; i < printNum.size(); i++){
                text += printNum.get(i) + " ";
            }
//            Print the numbers to the screen
            TextView lottoNumTV =(TextView)findViewById(R.id.numbersTextView);
            lottoNumTV.setText(text);
        }
        catch (NumberFormatException nf){
//            Display Error dialog if no number is entered
            new AlertDialog.Builder(this)
                    .setTitle("Invalid entry")
                    .setMessage("Enter numbers to continue")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    public void errorCheck(View view){
//        Check numbers are greater than 0
        if(Integer.parseInt(numOfNums) <= 0 || Integer.parseInt(maxNums) <= 0){
            new AlertDialog.Builder(this)
                    .setTitle("Invalid entry")
                    .setMessage("Select numbers greater than 0")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
}
