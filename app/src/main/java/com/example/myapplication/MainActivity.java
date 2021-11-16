package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button goAddTask = MainActivity.this.findViewById(R.id.goAddTask);
        Button goAllTask = MainActivity.this.findViewById(R.id.goAllTask);
        Button goPageSettings = MainActivity.this.findViewById(R.id.goPageSettings);
        Button task1 = MainActivity.this.findViewById(R.id.button_task1);
        Button task2 = MainActivity.this.findViewById(R.id.button_task2);
        Button task3 = MainActivity.this.findViewById(R.id.button_task3);
        TextView userNameView  = findViewById(R.id.home_page_userName);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = sharedPreferences.getString("userName","User");
        userNameView.setText(userName+"' Tasks");
        goAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddTaskActivity = new Intent(MainActivity.this, AddTask.class);
                startActivity(goToAddTaskActivity);
            }
        });

        goAllTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAllTasksActivity = new Intent(MainActivity.this, AllTask.class);
                startActivity(goToAllTasksActivity);
            }
        });
        task1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = task1.getText().toString();
                clickTask(taskName);
            }
        });
        task2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = task2.getText().toString();
                clickTask(taskName);
            }
        });
        task3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = task3.getText().toString();
                clickTask(taskName);
            }
        });
        goPageSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(MainActivity.this, SettingTask.class);
                startActivity(settingsIntent);
            }
        });
    }
    private void clickTask(String taskName) {
        Intent taskDetailsIntent = new Intent(MainActivity.this, DetailsTask.class);
        taskDetailsIntent.putExtra("taskName",taskName);
        startActivity(taskDetailsIntent);
    }
}