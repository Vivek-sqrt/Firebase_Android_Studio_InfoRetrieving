package com.example.inforetrieving;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    DatabaseReference firebaseDatabase;
    Button buttonGo, buttonShow;
    EditText editTextName, editTextAge, editTextUniversityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference("SavedData");

        buttonGo = findViewById(R.id.buttonSave);
        buttonShow = findViewById(R.id.buttonShow);
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextUniversityName = findViewById(R.id.editTextUniversityName);


        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveInfo();

            }
        });

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });
    }

    private void saveInfo() {
        String n,a,u;

        n = editTextName.getText().toString();
        a = editTextAge.getText().toString();
        u = editTextUniversityName.getText().toString();

        Info info = new Info(n,a,u);
        firebaseDatabase.child(n).setValue(info);
        Toast.makeText(MainActivity.this, "Data entered successfully", Toast.LENGTH_LONG).show();
        editTextName.setText("");
        editTextAge.setText("");
        editTextUniversityName.setText("");

    }
}
