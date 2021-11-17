package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class AddTask extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Button AddButton = (Button) findViewById(R.id.AddButton);
        AddButton.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Task Added",Toast.LENGTH_LONG).show();
            }
        });
    }
}