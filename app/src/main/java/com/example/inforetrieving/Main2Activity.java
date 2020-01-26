package com.example.inforetrieving;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference reference = firebaseDatabase.getReference("SavedData");

    ArrayList<String> arrayList = new ArrayList<>();  // array list for adapter
    String data="",datanew="";  // iteratively save data into list using data variable of string
    ListView listView;  //the list view for displaying data
    ArrayAdapter<String> adapter;  // adapter to convert the data from array list into the list view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arrayList);
        // adapter initialization
        // don't know why the second parameter is R.layout.support_simple_spinner_dropdown_item
        // the third parameter is just simply passing the arraylist into the adapter

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Info inf = postSnapshot.getValue(Info.class);
                    data = String.format("Name : %s , Age : %s",inf.getName(),inf.getAge());
                    datanew = String.format("University : %s",inf.getUniversityname());
                    arrayList.add(data);
                    arrayList.add(datanew);
                    arrayList.add("------------------------------------------------------------------------------");
                }
                listView.setAdapter(adapter); // setting the data of adapter into the list view
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
