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


        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);



        Button buttonthree = (Button) findViewById(R.id.buttonthree);
        TextView title = (TextView) findViewById(R.id.titleentry);
        TextView desc = (TextView) findViewById(R.id.desctask);


        buttonthree.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View v){

                String titletaken = title.getText().toString();
                String desctaken = desc.getText().toString();
                String team =  s.getSelectedItem().toString();

                Amplify.DataStore.query(
                        Team.class,Team.NAME.contains(team),
                        items -> {
                            while (items.hasNext()) {
                                Team item = items.next();
                                Task item1 = Task.builder().title(titletaken).body(desctaken).state("Active").teamId(item.getId()).build();
                                Amplify.DataStore.save(
                                        item1,
                                        success -> Log.i("COMO", "Saved item: "),
                                        error -> Log.e("Amplify", "Could not save item to DataStore", error)
                                );
                                Log.i("SHAMIKH", "Id was stored " );
                                Log.i("Amplify", "Id " + item.getId());
                            }
                        },
                        failure -> Log.e("Amplify", "Could not query DataStore", failure)
                );



                Toast.makeText(getApplicationContext(), "Task Added",Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
    public void back( View view){
        this.finish();

    }
}
