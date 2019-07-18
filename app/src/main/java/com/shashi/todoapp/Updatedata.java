package com.shashi.todoapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Updatedata extends AppCompatActivity {
    EditText edttitle,edtdis;
    Button update,delet;
    private DatabaseReference mDatabase;
    String titlesend,descsend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edttitle=findViewById(R.id.title1);
        edtdis=findViewById(R.id.dis1);
        update=findViewById(R.id.btnupdate);
        delet=findViewById(R.id.btndelete);

        final Intent i=getIntent();


        String gettitle=i.getStringExtra("title");
        String getdesc=i.getStringExtra("decs");
        final String id=i.getStringExtra("id");

        mDatabase=FirebaseDatabase.getInstance().getReference();

        edttitle.setText(gettitle);
        edtdis.setText(getdesc);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),id,Toast.LENGTH_LONG).show();
                updatenotes(id);
            }
        });

        delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),id,Toast.LENGTH_LONG).show();
                deletenotes(id);
            }
        });
    }

        private void updatenotes(String id){
           titlesend=edttitle.getText().toString();
           descsend=edtdis.getText().toString();

            Notes notes=new Notes(id,titlesend,descsend);
            mDatabase.child("Notes").child(id).setValue(notes).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(Updatedata.this,"Notes Update",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),Home.class));
                }
            });
        }
        private void deletenotes(String id){
        mDatabase.child("Notes").child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Updatedata.this,"Notes Remove",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),Home.class));
            }
        });
        }
}
