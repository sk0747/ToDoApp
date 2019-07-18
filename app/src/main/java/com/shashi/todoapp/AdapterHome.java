package com.shashi.todoapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.CourseViewHolder> {


    List<Notes> notesList;
   private Context context;
    public AdapterHome(List<Notes> notesList,Context context){
        this.context=context;
        this.notesList=notesList;
    }


    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item,parent,false);
        //pass the view of layout to class courseviewholder
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder courseViewHolder, int i) {

       Notes data=notesList.get(i);

        courseViewHolder.textView1.setText(data.getTitle());
        courseViewHolder.textView2.setText(data.getDiscription());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {

        TextView textView1, textView2;

        public CourseViewHolder(@NonNull View itemview) {
            super(itemview);
            textView1 =itemview.findViewById(R.id.title1);
            textView2 = itemview.findViewById(R.id.discr);
           itemview.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Notes notes=notesList.get(getAdapterPosition());
                   Intent i=new Intent(context,Updatedata.class);
                   i.putExtra("id",notes.id);
                   i.putExtra("title",notes.title);
                   i.putExtra("decs",notes.discription);
                   context.startActivity(i);
               }
           });

        }
    }
}
