package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class SettingTask extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_task);
        Button saveName= findViewById(R.id.save_name);
        EditText userNameField  = findViewById(R.id.user_name);
//        EditText team  = findViewById(R.id.user_name);
        saveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userNameField.getText().toString();
//
//                String team = s.getSelectedItem().toString();
//                String team = team.getText().toString();
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SettingTask.this);
                SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
                sharedPrefEditor.putString("userName",userName).apply();
//                sharedPreferences.edit().putString("team",team).apply();

                Intent mainIntent = new Intent(SettingTask.this, MainActivity.class);
                startActivity(mainIntent);
//
//                EditText name = findViewById(R.id.name);
//                String userName = name.getText().toString();
//                String team = s.getSelectedItem().toString();
//                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Settings.this);
//                sharedPreferences.edit().putString("username",userName).apply();
//                sharedPreferences.edit().putString("team",team).apply();
//                Toast.makeText(Settings.this,"Saved!", Toast.LENGTH_LONG).show();
//                finish();
            }

        });

    }
}