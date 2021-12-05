package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
public class SettingTask extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_task);
        Button saveNameOfUser = findViewById(R.id.button_save_name);
        EditText userNameField  = findViewById(R.id.input_user_name);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SettingTask.this);
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"Team-1", "Team-2", "Team-3"});
        Spinner teamSpinner = findViewById(R.id.chooseTeamSpinner);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamSpinner.setAdapter(dataAdapter);
        saveNameOfUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userNameField.getText().toString();
                sharedPrefEditor.putString("userName",userName);
                sharedPrefEditor.apply();
                switch(teamSpinner.getSelectedItem().toString()){
                    case "Team-1":
                        sharedPrefEditor.putString("teamName","389380ef-2b41-4bae-bf3d-a5be6300857c");
                        sharedPrefEditor.apply();
                        break;
                    case "Team-2":
                        sharedPrefEditor.putString("teamName","2d6f0cb0-eb28-41cb-a68d-fa0404405ce4");
                        sharedPrefEditor.apply();
                        break;
                    case "Team-3":
                        sharedPrefEditor.putString("teamName","6c9ff776-74fc-40f4-b57a-14e85e3ad8fd");
                        sharedPrefEditor.apply();
                        break;
                }
                Intent mainIntent = new Intent(SettingTask.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

    }
}