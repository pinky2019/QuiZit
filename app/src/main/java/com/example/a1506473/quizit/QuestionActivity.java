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

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private Button start;

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

        textView.setText("      Welcome !   \n "+ "     "+email);

        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(QuestionActivity.this,Start.class);
        startActivity(intent);

    }
}
