package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
public class DetailsTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_task);
        Intent detailIntent=getIntent();
        String taskName  = detailIntent.getStringExtra("taskName");
        TextView taskNameView = findViewById(R.id.task_details_title_taskName_view);
        taskNameView.setText(taskName);
    }
}