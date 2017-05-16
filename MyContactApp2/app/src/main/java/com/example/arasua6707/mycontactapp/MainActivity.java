package com.example.arasua6707.mycontactapp;

import android.content.Context;
import android.database.Cursor;
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
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        //Add the layout vars
        editName = (EditText) findViewById(R.id.editText_name);
    }

    public void addData(View v){
        Log.d("MyContact", "Successful data insertion");
        boolean isInserted = myDb.insertData(editName.getText().toString());



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
            //Output message using Log.d and Toast

            return;
        }
        StringBuffer buffer = new StringBuffer();
        //setup a loop with Cursor (res) using moveToNext
        //append each COL to the buffer
        //display the message using showMessage
        showMessage("Data", buffer.toString());
    }

    private void showMessage(String title, String message) {
        //AlertDialg.builder
    }
}
