package com.example.arasua6707.mycontactapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editAddress;
    EditText editNumber;
    EditText editSearch;
    Button btnAddData;

    String[] entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        //Add the layout vars
        editName = (EditText) findViewById(R.id.editText_name);
        editAddress = (EditText) findViewById(R.id.editText_address);
        editNumber = (EditText) findViewById(R.id.editText_number);
        editSearch = (EditText) findViewById(R.id.editText_search);

        entries = new String[] {"Name: ", "Address: ", "Number: "};
    }

    public void addData(View v){
        //Log.d("MyContact", "Successful data insertion");
        boolean isInserted = myDb.insertData(editName.getText().toString(), editAddress.getText().toString(), editNumber.getText().toString());



        if (isInserted == true){
            Log.d("MyContact", "Successful data insertion");
            Context context = MainActivity.this;
            CharSequence text = "Success!";
           int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else{
            Log.d("MyContact", "Data insertion failure");
            Context context = getApplicationContext();
            CharSequence text = "YOUR TOAST WAS BURNT :(";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }
    }

    public void viewData (View v){
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0){
            showMessage("Error", "No data found in database");
            //Output message using Log.d and Toast. done
            Log.d("MyContact", "No data found in database");
            Context context = getApplicationContext();
            CharSequence text = "No data to display";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();


            return;
        }
        StringBuffer buffer = new StringBuffer();
        //setup a loop with Cursor (res) using moveToNext
        if(res != null){
            res.moveToFirst();
            for(int i = 0; i < res.getCount(); i++){
                for(int j = 1; j<=3;j++){
                    buffer.append(entries[j-1]);
                    buffer.append(res.getString(j));
                    buffer.append("\n");

                }
                buffer.append("\n");
                res.moveToNext();
            }
            showMessage("Data", buffer.toString());
        }
        //append each COL to the buffer
        //display the message using showMessage
    }

    public void searchData(View v){
        Cursor res = myDb.getAllData();
        StringBuffer buffer = new StringBuffer();
        //setup a loop with Cursor (res) using moveToNext
        if (res != null) {
            res.moveToFirst();
            for (int i = 0; i < res.getCount(); i++) {
                if (res.getString(1).equals(editSearch.getText().toString())) {
                    for (int j = 0; j <= res.getColumnNames().length; j++) {
                        buffer.append(entries[j]);
                        buffer.append(res.getString(j));
                        buffer.append("\n");


                    }
                    buffer.append("\n");
                    res.moveToNext();
                }
                showMessage("Contact: " , buffer.toString());
            }
            //append each COL to the buffer
            //display the message using showMessage
        }
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
