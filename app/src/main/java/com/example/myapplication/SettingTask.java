package com.example.myapplication;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
public class SettingTask extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_task);
        Button saveName= findViewById(R.id.button_save_name);
        EditText userNameField  = findViewById(R.id.input_user_name);
        saveName.setOnClickListener(v -> {
            String userName = userNameField.getText().toString();
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SettingTask.this);
            SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
            sharedPrefEditor.putString("userName",userName);
            sharedPrefEditor.apply();
            Intent mainIntent = new Intent(SettingTask.this, MainActivity.class);
            startActivity(mainIntent);
        });

    }
}