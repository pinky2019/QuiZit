package com.example.a1506473.quizit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a1506473.quizit.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;

public class Start extends AppCompatActivity implements View.OnClickListener{

    Button btnplay;


   // FirebaseDatabase  database;
   private DatabaseReference questions;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //database=FirebaseDatabase
        questions=FirebaseDatabase.getInstance().getReference().child("Questions");
         btnplay=(Button)findViewById(R.id.start);



         btnplay.setOnClickListener(this);

    }

    private void loadQuestion(String categoryId)
    {

        //first clear list if have old questions
        if(Common.questionList.size() > 0)
        {
            Common.questionList.clear();
        }


        questions.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot postSnapshot : dataSnapshot.getChildren())
                        {
                            Question ques =postSnapshot.getValue(Question.class);
                            Common.questionList.add(ques);

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



        //random list
        Collections.shuffle(Common.questionList);
    }

    @Override
    public void onClick(View view) {
        loadQuestion(Common.categoryId);

        Intent intent=new Intent(this,playing.class);
        startActivity(intent);

    }
}
