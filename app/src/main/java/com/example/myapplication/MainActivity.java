package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    RecyclerView recyclerView = findViewById( R.id.RV_main );
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences( this );

        try {
            Amplify.addPlugin( new AWSApiPlugin() );
            Amplify.addPlugin( new AWSDataStorePlugin() );
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());
            Log.i( "Tutorial", "Initialized Amplify" );
        } catch (AmplifyException failure) {
            Log.e( "Tutorial", "Could not initialize Amplify", failure );
        }
        Amplify.Auth.signInWithWebUI(
                this,
                result -> Log.i("AuthQuickStart", result.toString()),
                error -> Log.e("AuthQuickStart", error.toString())
        );
        Amplify.DataStore.observe( Task.class,
                started -> Log.i( "Tutorial", "Observation began." ),
                change -> Log.i( "Tutorial", change.item().toString() ),
                failure -> Log.e( "Tutorial", "Observation failed.", failure ),
                () -> Log.i( "Tutorial", "Observation complete." )
        );
//        ArrayList<AuthUserAttribute> attributes = new ArrayList<>();
//        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.email(), "my@email.com"));
//        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.phoneNumber(), "+15551234567"));
//
//        Amplify.Auth.signUp(
//                "username",
//                "Password123",
//                AuthSignUpOptions.builder().userAttributes(attributes).build(),
//                result -> Log.i("AuthQuickstart", result.toString()),
//                error -> Log.e("AuthQuickstart", error.toString())
//        );
//        Amplify.Auth.confirmSignIn(
//                "confirmation code received via SMS",
//                result -> Log.i("AuthQuickstart", result.toString()),
//                error -> Log.e("AuthQuickstart", error.toString())
//        );
        List<Task> Tasks = new ArrayList();
        Amplify.DataStore.query(
                Task.class,
                items -> {
                    Tasks.clear();
                    while (items.hasNext()) {
                        Task item = items.next();
                        Tasks.add( item );
                        Log.i( "Amplify", "Id " + item.getTitle() );
                    }
                },
                failure -> Log.e( "Amplify", "Could not query DataStore", failure )
        );

        TaskAdapter taskAdapter = new TaskAdapter( Tasks, this );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this );
        linearLayoutManager.canScrollVertically();
        linearLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        recyclerView.setLayoutManager( linearLayoutManager );
        recyclerView.setAdapter( taskAdapter );

        Button addTask = MainActivity.this.findViewById(R.id.button_addButton);
        Button allTasks = MainActivity.this.findViewById(R.id.button_allButton);
        Button settingTask = MainActivity.this.findViewById(R.id.button_settingButton);
        TextView userNameView  = findViewById(R.id.input_user_name);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddTaskActivity = new Intent( MainActivity.this, AddTask.class );
                startActivity( goToAddTaskActivity );
            }
        });
        allTasks.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goToAllTasksActivity = new Intent( MainActivity.this, AllTask.class );
                        startActivity( goToAllTasksActivity );
                    }
                } );
        settingTask.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent settingsIntent = new Intent( MainActivity.this, SettingTask.class );
                        startActivity( settingsIntent );
                    }
        } );
    }
}