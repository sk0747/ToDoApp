package com.shashi.todoapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNote extends AppCompatActivity {
     EditText edttitle,edtdis;
     Button add;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        edttitle=findViewById(R.id.title);
        edtdis=findViewById(R.id.dis);
        add=findViewById(R.id.btn);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addnotes();
            }
        });

    }
    public void addnotes(){
        String id=mDatabase.push().getKey();
        String tit=edttitle.getText().toString();
        String disc=edtdis.getText().toString();
       if(tit.isEmpty()){
           edttitle.setError("Enter Title");

       }else if(disc.isEmpty()){
           edtdis.setError("Enter Discription");
       }
       else {
           Notes notes = new Notes(id, tit, disc);

           mDatabase.child("Notes").child(id).setValue(notes)
                   .addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           Toast.makeText(AddNote.this, "Notes Added", Toast.LENGTH_LONG).show();
                           startActivity(new Intent(getApplicationContext(), Home.class));
                       }
                   });
       }

    }
}
