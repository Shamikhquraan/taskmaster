package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.MainActivity;

public class SettingTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_task);


        Button saveNameOfUser = findViewById(R.id.button_save_name);// to save the name of user in the input Field
        EditText userNameField  = findViewById(R.id.input_user_name);// enter the name of user .

        saveNameOfUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userNameField.getText().toString();// target the name of user from the input after clicking the button
                // save the user name in the SharedPreferences (like local storage in js).
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SettingTask.this);
//                sharedPreferences.edit().putString("userName",userName).apply();
                SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
                sharedPrefEditor.putString("userName",userName);
                sharedPrefEditor.apply();
                // return to the main page after save the name of user
                Intent mainIntent = new Intent(SettingTask.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

    }
}