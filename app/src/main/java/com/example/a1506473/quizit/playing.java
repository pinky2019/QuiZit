package com.example.a1506473.quizit;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class playing extends AppCompatActivity implements View.OnClickListener{


    final static long INTERVAl=1000;
    final static long TIMEOUT=5000;
    CountDownTimer mcountDown;
 int index=0,totalQuestion,score=0,thisQuestion=0,correctAnswer;

    TextView txtScore1,question_text1;
    Button btnOptionA,btnOptionB,btnOptionC,btnOptionD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);


        txtScore1=findViewById(R.id.score);
        question_text1=findViewById(R.id.quest);

        btnOptionA=findViewById(R.id.ansA);
        btnOptionB=findViewById(R.id.ansB);
        btnOptionC=findViewById(R.id.ansC);
        btnOptionD=findViewById(R.id.ansD);


        btnOptionA.setOnClickListener(this);
        btnOptionB.setOnClickListener(this);
        btnOptionC.setOnClickListener(this);
        btnOptionD.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mcountDown.cancel();

        if(index < totalQuestion)
        {
            Button clickedButton=(Button)view;
            if(clickedButton.getText().equals(QuesLibrary.questionlist.get(index).getCorrectAnswer()))
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
            txtScore1.setText(String.format("%d",score));
        }

    }

    private void showQuestion(int index)
    {
        if(index<totalQuestion)
        {
            //not the final question
            thisQuestion++;
            question_text1.setText(QuesLibrary.questionlist.get(index).getQuestion());
            btnOptionA.setText(QuesLibrary.questionlist.get(index).getAnswerA());
            btnOptionB.setText(QuesLibrary.questionlist.get(index).getAnswerB());
            btnOptionC.setText(QuesLibrary.questionlist.get(index).getAnswerC());
            btnOptionD.setText(QuesLibrary.questionlist.get(index).getAnswerD());

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
    protected void onResume()
    {
        super.onResume();
        totalQuestion= QuesLibrary.questionlist.size();


        mcountDown=new CountDownTimer(INTERVAl,TIMEOUT) {
            @Override
            public void onTick(long l) {

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
