package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button goToAddPage = (Button) findViewById(R.id.goToAddPage);//button 1
        Button goToAllPage = (Button) findViewById(R.id.goToAllPage); // button 2
        goToAddPage.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, AddTask.class));
            }
        });
        goToAllPage.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, AllTask.class));
            }
        });
    }
}