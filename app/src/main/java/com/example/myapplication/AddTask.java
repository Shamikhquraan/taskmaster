package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
import java.util.ArrayList;
import java.util.List;
public class AddTask extends AppCompatActivity {
    private Spinner teamSpinner;
    private List<Team> teams =  new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        EditText titleView,bodyView,steteView;
        titleView = findViewById(R.id.titlePlace);
        bodyView = findViewById(R.id.DescPace);
        steteView = findViewById(R.id.StatePlace);
        Button addButton = findViewById(R.id.addButton);

// for create spinner
        ArrayAdapter<String> dataAdapterPort = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"Team-1", "Team-2", "Team-3"});
        teamSpinner = findViewById(R.id.teamSpinner);
        dataAdapterPort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamSpinner.setAdapter(dataAdapterPort);
        getTeams();

        addButton.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
          switch(teamSpinner.getSelectedItem().toString()){
                    case "Team-1":
                        Task task = Task.builder().title(titleView.getText().toString()).body(bodyView.getText().toString()).state(steteView.getText().toString()).teamId(teams.get(2).getId()).build();
                        saveToAPI(task);
                        saveToDatastore(task);
                        break;
                    case "Team-2":
                        Task task2 = Task.builder().title(titleView.getText().toString()).body(bodyView.getText().toString()).state(steteView.getText().toString()).teamId(teams.get(1).getId()).build();
                        saveToAPI(task2);
                        saveToDatastore(task2);
                        break;
                    case "Team-3":
                        Task task3 = Task.builder().title(titleView.getText().toString()).body(bodyView.getText().toString()).state(steteView.getText().toString()).teamId(teams.get(0).getId()).build();
                        saveToAPI(task3);
                        saveToDatastore(task3);
                        break;
                }

                Toast.makeText(getApplicationContext(), "Task Added",Toast.LENGTH_LONG).show();
                Intent mainIntent = new Intent(AddTask.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }

    private void getTeams() {
        Amplify.API.query(
                ModelQuery.list(Team.class),
                response -> {
                    for (Team team : response.getData()) {
                        this.teams.add(team);
                        Log.i("MyAmplifyApp", team.getName());
                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
    }

    public void saveToAPI(Task task){
        Amplify.API.mutate(ModelMutation.create(task),
                success -> Log.i("Tutorial", "Saved item: " + success.getData()),
                error -> Log.e("Tutorial", "Could not save item to API", error)
        );
    }

    public void saveToDatastore(Task task){
        Amplify.DataStore.save(task,
                success -> Log.i("Tutorial", "Saved item: " + success.item()),
                error -> Log.e("Tutorial", "Could not save item to DataStore", error)
        );
    }
}