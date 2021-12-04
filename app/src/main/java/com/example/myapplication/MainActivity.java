package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        TaskDataBase db = TaskDataBase.getInstance(this);
//        TaskDao taskDao = db.taskDao();
//        List<Task> tasksDB = taskDao.getAll();

        RecyclerView recyclerView = findViewById(R.id.RV_main);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);



// Initialized Amplify
        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());
            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException failure) {
            Log.e("Tutorial", "Could not initialize Amplify", failure);
        }
        Amplify.DataStore.observe(Task.class,
                started -> Log.i("Tutorial", "Observation began."),
                change -> Log.i("Tutorial", change.item().toString()),
                failure -> Log.e("Tutorial", "Observation failed.", failure),
                () -> Log.i("Tutorial", "Observation complete.")
        );


// getting data from database .
        String teamName = sharedPreferences.getString("teamName","2a38e3e0-7023-4cb5-9a31-a3979698cf13");
        List<Task> Tasks = new ArrayList();
        Amplify.DataStore.query(
                Task.class,Task.TEAM_ID.eq(teamName),
                items -> {
                    Tasks.clear();
                    while (items.hasNext()) {
                        Task item = items.next();
                        Tasks.add(item);
                        Log.i("Amplify", "Id " + item.getTitle());
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );






        TaskAdapter taskAdapter = new TaskAdapter(Tasks, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.canScrollVertically();
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(taskAdapter);


        Button addTask = MainActivity.this.findViewById(R.id.button_addTask);
        Button allTasks = MainActivity.this.findViewById(R.id.button_allTasks);


        Button settings = MainActivity.this.findViewById(R.id.button_settings);


        TextView userNameView  = findViewById(R.id.input_user_name);



        String userName = sharedPreferences.getString("userName","User");
        userNameView.setText(userName+"' Tasks");
//        teamNameView.setText(teamName);


        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddTaskActivity = new Intent(MainActivity.this, AddTask.class);
                startActivity(goToAddTaskActivity);


                // to add new teams
//                Team item1 = Team.builder()
//                        .name("team1")
//                        .build();
//                Amplify.DataStore.save(
//                        item1,
//                        success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
//                        error -> Log.e("Amplify", "Could not save item to DataStore", error)
//                );
//                Team item2 = Team.builder()
//                        .name("team2")
//                        .build();
//                Amplify.DataStore.save(
//                        item2,
//                        success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
//                        error -> Log.e("Amplify", "Could not save item to DataStore", error)
//                );
//                Team item3 = Team.builder().name("team3").build();
//                Amplify.DataStore.save(
//                        item3,
//                        success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
//                        error -> Log.e("Amplify", "Could not save item to DataStore", error)
//                );

            }
        });

        allTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAllTasksActivity = new Intent(MainActivity.this, AllTask.class);
                startActivity(goToAllTasksActivity);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(MainActivity.this, SettingTask.class);
                startActivity(settingsIntent);
            }
        });

    }
}