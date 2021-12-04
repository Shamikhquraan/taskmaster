package com.example.myapplication;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
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

        Task item = Task.builder()
                .title("Lorem ipsum dolor sit amet")
                .body("Lorem ipsum dolor sit amet")
                .state("Lorem ipsum dolor sit amet")
                .teamId("a3f4095e-39de-43d2-baf4-f8c16f0f6f4d")
                .build();
        Amplify.DataStore.save(
                item,
                success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
                error -> Log.e("Amplify", "Could not save item to DataStore", error)
        );


        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);


        Button addButton = (Button) findViewById(R.id.addTask);
        TextView title = (TextView) findViewById(R.id.taskTitleText);
        TextView desc = (TextView) findViewById(R.id.taskDescText);


        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String titletaken = title.getText().toString();
                String desctaken = desc.getText().toString();
                String team = s.getSelectedItem().toString();

                Amplify.DataStore.query(
                        Team.class, Team.NAME.contains(team),
                        items -> {
                            while (items.hasNext()) {
                                Team item = items.next();
                                Task item1 = Task.builder().title(titletaken).body(desctaken).state("Active").teamId(item.getId()).build();
                                Amplify.DataStore.save(
                                        item1,
                                        success -> Log.i("COMO", "Saved item: "),
                                        error -> Log.e("Amplify", "Could not save item to DataStore", error)
                                );
                                Log.i("EMAM", "Id was stored ");
                                Log.i("Amplify", "Id " + item.getId());
                            }
                        },
                        failure -> Log.e("Amplify", "Could not query DataStore", failure)
                );


                Toast.makeText(getApplicationContext(), "Task Added", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

    public void back(View view) {
        this.finish();

    }

}
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_task);
//        EditText title,body,state;
//        title = findViewById(R.id.taskTitleText);
//        body = findViewById(R.id.taskDescText);
//        state = findViewById(R.id.taskStateText);
//        Button addTask = findViewById(R.id.addTask);
//        addTask.setOnClickListener(new  View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//           // AddToDataBase
//                Task item = Task.builder()
//                        .title(title.getText().toString())
//                        .body(body.getText().toString())
//                        .state(state.getText().toString())
//                        .build();
//                Amplify.DataStore.save(
//                        item,
//                        success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
//                        error -> Log.e("Amplify", "Could not save item to DataStore", error)
//                );
//
//                Toast.makeText(getApplicationContext(), "Task Added",Toast.LENGTH_LONG).show();
//                Intent mainIntent = new Intent(AddTask.this, MainActivity.class);
//                startActivity(mainIntent);
//            }
//        });
//    }


