package com.example.arasua6707.mycontactapp;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by arasua6707 on 5/22/2017.
 */
public class SearchActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editSearch;
    Button btnSearchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    public void viewData(View v) {
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
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
        if (res != null) {
            res.moveToFirst();
            for (int i = 0; i < res.getCount(); i++) {
                for (int j = 0; j < res.getColumnNames().length; j++) {
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

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
}
