package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class AddTask extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        EditText title,body,state;
        title = findViewById(R.id.taskTitleText);
        body = findViewById(R.id.taskDescText);
        state = findViewById(R.id.taskStateText);
        Button addTask = findViewById(R.id.addTask);
        TaskDataBase db = TaskDataBase.getInstance(this);
        TaskDao taskDao = db.taskDao();
        addTask.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Task task = new Task(title.getText().toString(),body.getText().toString(),state.getText().toString());
                taskDao.insert(task);
                Toast.makeText(getApplicationContext(), "Task Added !",Toast.LENGTH_LONG).show();
                Intent mainIntent = new Intent(AddTask.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }
}