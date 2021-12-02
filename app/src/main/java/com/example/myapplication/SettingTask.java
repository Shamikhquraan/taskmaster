package com.example.myapplication;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
        EditText userNameField = findViewById(R.id.input_user_name);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SettingTask.this);
//                sharedPreferences.edit().putString("userName",userName).apply();
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();

        // create spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"Team 1", "Team 2", "Team 3"});
        Spinner teamSpinner = findViewById(R.id.chooseTeamSpinner);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamSpinner.setAdapter(dataAdapter);
        saveNameOfUser.setOnClickListener(v -> {
            String userName = userNameField.getText().toString();
            sharedPrefEditor.putString("userName", userName);
            sharedPrefEditor.apply();

            // to select the team
            switch (teamSpinner.getSelectedItem().toString()) {
                case "Team 1":
                    sharedPrefEditor.putString("teamName", "59523fe4-b088-4967-a9d3-af07c040cf42");
                    sharedPrefEditor.apply();
                    break;
                case "Team 2":
                    sharedPrefEditor.putString("teamName", "2a490f92-db90-463c-9269-ebd6d5e31233");
                    sharedPrefEditor.apply();
                    break;
                case "Team 3":
                    sharedPrefEditor.putString("teamName", "2a38e3e0-7023-4cb5-9a31-a3979698cf13");
                    sharedPrefEditor.apply();
                    break;
            }

            // return to the main page after save the name of user
            Intent mainIntent = new Intent(SettingTask.this, MainActivity.class);
            startActivity(mainIntent);
//                finish();  we can use that instead of the intent
        });

    }
}