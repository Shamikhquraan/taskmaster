package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TaskDataBase db = TaskDataBase.getInstance(this);
        TaskDao taskDao = db.taskDao();
        List<Task> tasksDB = taskDao.getAll();
        RecyclerView recyclerView = findViewById(R.id.RV_main);

        //String body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras felis massa, elementum a nibh sed, sodales posuere nunc. Vivamus eget ante malesuada, fermentum tellus eget, dignissim enim. Duis felis enim, facilisis in tortor eget, pellentesque tristique dolor. ";
        // List tasks = new ArrayList<>();
        // Task task1 = new Task("Title: Take A Shower", body, "State: New" );
        //Task task2 = new Task("Title: BreakFast", body, "State: Assigned" );
        // Task task3 = new Task("Title: Shopping", body, "State: In progress" );
        // Task task4 = new Task("Title: Fix My car", body, "State: Complete" );
        // tasks.add(task1);
        // tasks.add(task2);
        // tasks.add(task3);
        // tasks.add(task4);

        TaskAdapter taskAdapter = new TaskAdapter(tasksDB, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.canScrollVertically();
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(taskAdapter);

        Button goAddTask = MainActivity.this.findViewById(R.id.goAddTask);
        Button goAllTask = MainActivity.this.findViewById(R.id.goAllTask);
        Button goPageSettings = MainActivity.this.findViewById(R.id.goPageSettings);

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

        goPageSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(MainActivity.this, SettingTask.class);
                startActivity(settingsIntent);
            }
        });

    }

}