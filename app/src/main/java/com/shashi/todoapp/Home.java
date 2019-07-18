package com.shashi.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<Notes> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);

        recyclerView=findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        //use for making list by using linear
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(Home.this);
        recyclerView.setLayoutManager(layoutManager);
        final AdapterHome adapterHome=new AdapterHome(list,this);
        recyclerView.setAdapter(adapterHome);

        FloatingActionButton fab = findViewById(R.id.fab);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Notes");

        //change in data base
       databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               //get data of all value
               for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
               {
                   Notes notes=dataSnapshot1.getValue(Notes.class);
                   list.add(notes);

               }
               adapterHome.notifyDataSetChanged();

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this,AddNote.class);
                startActivity(i);
            }
        });
    }

}
