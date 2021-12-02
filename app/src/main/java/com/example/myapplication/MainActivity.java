package com.example.myapplication;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Team;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
//        Amplify.DataStore.observe(Task.class ,
//                started -> Log.i("Tutorial", "Observation began."),
//                change -> Log.i("Tutorial", change.item().toString()),
//                failure -> Log.e("Tutorial", "Observation failed.", failure),
//                () -> Log.i("Tutorial", "Observation complete.")
//        );
//
ArrayList<Task> Tasks = new ArrayList<>();
        {  Amplify.API.query(
                    ModelQuery.list(Team.class),
                    response -> {
                        for (Team team : response.getData()) {
//                        this.teams.add(team);
                            Log.i("MyAmplifyApp", String.valueOf(team));
                        }
                    },
                    error -> Log.e("MyAmplifyApp", "Query failure", error)
            );}

        TaskAdapter taskAdapter = new TaskAdapter(Tasks, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.canScrollVertically();
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(taskAdapter);

        Button addTask = MainActivity.this.findViewById(R.id.button_addTask);
        Button allTasks = MainActivity.this.findViewById(R.id.button_allTasks);
        Button settings = MainActivity.this.findViewById(R.id.button_settings);


        TextView userNameView  = findViewById(R.id.home_page_userName);
        String userName = sharedPreferences.getString("userName","User");
        userNameView.setText(userName+" Tasks");
        addTask.setOnClickListener(v -> {
            Intent goToAddTaskActivity = new Intent(MainActivity.this, AddTask.class);
            startActivity(goToAddTaskActivity);
           //  to add new teams
                Team item1 = Team.builder()
                        .name("team1")
                        .build();
                Amplify.DataStore.save(
                        item1,
                        success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
                        error -> Log.e("Amplify", "Could not save item to DataStore", error)
                );
                Team item2 = Team.builder()
                        .name("team2")
                        .build();
                Amplify.DataStore.save(
                        item2,
                        success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
                        error -> Log.e("Amplify", "Could not save item to DataStore", error)
                );
                Team item3 = Team.builder().name("team3").build();
                Amplify.DataStore.save(
                        item3,
                        success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
                        error -> Log.e("Amplify", "Could not save item to DataStore", error)
                );
        });
        allTasks.setOnClickListener(v -> {
            Intent goToAllTasksActivity = new Intent(MainActivity.this, AllTask.class);
            startActivity(goToAllTasksActivity);
        });

        settings.setOnClickListener(v -> {
            Intent settingsIntent = new Intent(MainActivity.this, SettingTask.class);
            startActivity(settingsIntent);
        });

    }


}