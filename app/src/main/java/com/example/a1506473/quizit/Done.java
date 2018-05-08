package com.example.a1506473.quizit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Done extends AppCompatActivity{


    Button btntryagain;
    TextView txtResultScore,getTextQuestion;


    FirebaseDatabase database;
    DatabaseReference question_score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        database=FirebaseDatabase.getInstance();
        question_score=database.getReference("Question_score");

        btntryagain=(Button)findViewById(R.id.tr);

        btntryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Done.this,QuestionActivity.class);
                startActivity(intent);

            }

        });

        Bundle extra=getIntent().getExtras();

        if(extra != null)
        {
            int score=extra.getInt("SCORE");
            int totalQuestion=extra.getInt("TOTAL");
            int correctAnswer=extra.getInt("CORRECT");
            txtResultScore.setText(String.format("SCORE : %d",score));
            getTextQuestion.setText(String.format("PASSED : %d/%d",correctAnswer,totalQuestion));

        }
    }

}




