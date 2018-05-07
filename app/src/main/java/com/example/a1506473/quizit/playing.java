package com.example.a1506473.quizit;


import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class playing extends AppCompatActivity implements View.OnClickListener {


    final static long INTERVAL =1000;
    final static long TIMEOUT =5000;


    CountDownTimer mcountDown;

    int index=0,score=0,thisQuestion=0,totalQuestion,correctAnswer;

   //FirebaseDatabase database;
   //DatabaseReference  questions;
   Button btnA,btnB,btnC,btnD;
   TextView txtScore,question_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);


        Toast.makeText(this,"game start",Toast.LENGTH_LONG).show();

        //database =FirebaseDatabase.getInstance();
        //questions =database.getReference("Questions");

        txtScore=(TextView)findViewById(R.id.score);
        question_text=(TextView)findViewById(R.id.Question);

        btnA=(Button)findViewById(R.id.A);
        btnB=(Button)findViewById(R.id.B);
        btnC=(Button)findViewById(R.id.C);
        btnD=(Button)findViewById(R.id.D);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        mcountDown.cancel();

        if(index < totalQuestion)
        {
            Button clickedButton=(Button)view;
            if(clickedButton.getText().equals(com.example.a1506473.quizit.Common.questionList.get(index).getCorrectAnswer()))
            {
                //choose correct answer
                score+=10;
                correctAnswer++;
                showQuestion(++index);
            }
            else
            {
                //choose wong Answer
                Intent intent=new Intent(this,Done.class);
                Bundle datasend=new Bundle();
                datasend.putInt("SCORE",score);
                datasend.putInt("TOTAL",totalQuestion);
                datasend.putInt("CORRECT",correctAnswer);
                intent.putExtras(datasend);
                startActivity(intent);
                finish();
            }
            txtScore.setText(String.format("%d",score));
        }

    }

    private void showQuestion(int index)
    {
        if(index<totalQuestion)
        {
            thisQuestion++;
            question_text.setText(com.example.a1506473.quizit.Common.questionList.get(index).getQuestion().toString());
            btnA.setText(com.example.a1506473.quizit.Common.questionList.get(index).getAnswerA().toString());
            btnB.setText(com.example.a1506473.quizit.Common.questionList.get(index).getAnswerB().toString());
            btnC.setText(com.example.a1506473.quizit.Common.questionList.get(index).getAnswerC().toString());
            btnD.setText(com.example.a1506473.quizit.Common.questionList.get(index).getAnswerD().toString());

            mcountDown.start();
        }
        else
        {
            //if it is final qestion
            Intent intent=new Intent(this,Done.class);
            Bundle datasend=new Bundle();
            datasend.putInt("SCORE",score);
            datasend.putInt("TOTAL",totalQuestion);
            datasend.putInt("CORRECT",correctAnswer);
            intent.putExtras(datasend);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        totalQuestion= com.example.a1506473.quizit.Common.questionList.size();

        mcountDown=new CountDownTimer(TIMEOUT,INTERVAL) {
            @Override
            public void onTick(long minisec) {

            }

            @Override
            public void onFinish() {
            mcountDown.cancel();
            showQuestion(++index);
            }
        };
        showQuestion(++index);
    }
}
