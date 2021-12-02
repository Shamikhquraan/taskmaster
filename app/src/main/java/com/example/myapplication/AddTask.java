package com.example.myapplication;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Spinner teamSpinner;
        EditText title,body,state;
        title = findViewById(R.id.taskTitle);
        body = findViewById(R.id.taskDescription);
        state = findViewById(R.id.taskState);
        Button addTask = findViewById(R.id.button_addTask_activity_addTask);

        ArrayAdapter<String> teams = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"Team 1", "Team 2", "Team 3"});
                teamSpinner = findViewById(R.id.teamSpinner);
        teams.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        addTask.setOnClickListener(v -> {
            String titleOne = title.getText().toString();
            String bodyOne = body.getText().toString();
            String stateOne = "TRUE";
            String team =  teamSpinner.getSelectedItem().toString();
            Amplify.DataStore.query(
                    Team.class,Team.NAME.contains(team),
                    items -> {
                        while (items.hasNext()) {
                            Team item = items.next();
                            Task itemOne = Task.builder().title(titleOne).body(bodyOne).state(stateOne).teamId(item.getId()).build();
                            Amplify.DataStore.save(
                                    itemOne,
                                    success -> Log.i("COMO", "Saved item: "),
                                    error -> Log.e("Amplify", "Could not save item to DataStore", error)
                            );
                            Log.i("Is stored", "Id was stored " );
                            Log.i("Amplify", "Id " + itemOne.getId());
                        }
                    },
                    failure -> Log.e("Amplify", "Could not query DataStore", failure)
            );
            Toast.makeText(getApplicationContext(), "Task Added",Toast.LENGTH_LONG).show();
            finish();
        });
    }
    public void back( View view){
        this.finish();

    }
}
