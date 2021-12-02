package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class DetailsTask extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_task);
        // create intent to get passing extra from main activity
        Intent detailIntent=getIntent();
        String taskName  = detailIntent.getStringExtra("taskName");

// set the name of the task in the  TextView
        TextView taskNameView = findViewById(R.id.task_details_title_taskName_view);
        taskNameView.setText(taskName);
    }
}