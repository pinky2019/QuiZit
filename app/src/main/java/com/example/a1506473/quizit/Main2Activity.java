package com.example.a1506473.quizit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;




public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonLogIn;
    private EditText editTextEmail;
    private EditText editTextPassword;


    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        buttonLogIn=(Button) findViewById(R.id.Login);


        progressDialog=new ProgressDialog(this);

        firebaseAuth=FirebaseAuth.getInstance();


        buttonLogIn.setOnClickListener(this);



    }

    private  void userLogin()
    {
        final String email=editTextEmail.getText().toString().trim();

        String password=editTextPassword.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Registering Please wait..");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        progressDialog.dismiss();

                        if(task.isSuccessful())
                        {
                            //start the profile activity
                            Intent intent=new Intent(Main2Activity.this,QuestionActivity.class);
                            intent.putExtra("ema",email);
                            startActivity(intent);
                           // startActivity(new Intent(Main2Activity.this,QuestionActivity.class));
                        }

                        else
                        {
                            Toast.makeText(Main2Activity.this,"Invalid credentials",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view==buttonLogIn)
        {
            userLogin();
        }


    }
}
