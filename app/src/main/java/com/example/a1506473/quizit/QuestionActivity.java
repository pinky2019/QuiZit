package com.example.a1506473.quizit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private Button start;
    private DatabaseReference questions;

   private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent intent=getIntent();
        String email=intent.getStringExtra("ema");
        textView=(TextView)findViewById(R.id.textViewWelcome);
        start=(Button)findViewById(R.id.start);

     //FirebaseUser user=firebaseAuth.getCurrentUser();

        questions= FirebaseDatabase.getInstance().getReference().child("Questions");

        textView.setText("      Welcome !   \n "+ "     "+email);

        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        questions.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Question ques = postSnapshot.getValue(Question.class);
                    QuesLibrary.questionlist.add(ques);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //random list
        Collections.shuffle(QuesLibrary.questionlist);


        Intent intent=new Intent(this,playing.class);

        startActivity(intent);

    }
}
