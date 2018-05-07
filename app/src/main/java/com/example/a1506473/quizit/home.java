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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity implements View.OnClickListener{


    private Button buttonRegister;
    private Button buttonSignin;
    private EditText editTextEmail;
    private EditText editTextPassword;


    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth=FirebaseAuth.getInstance();

        progressDialog=new ProgressDialog(this);

        buttonRegister=(Button)findViewById(R.id.buttonSignUp);

        editTextEmail=(EditText)findViewById(R.id.editTextEmail);

        editTextPassword=(EditText)findViewById(R.id.editTextPassword);

        buttonSignin=(Button)findViewById(R.id.buttonSignIn);

        buttonRegister.setOnClickListener(this);

        buttonSignin.setOnClickListener(this);

    }

    private void registerUser()
    {
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering Please wait..");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //user is successfully registered and logged in
                            //we will start the profile activity here
                            //right now lets display a toast message
                            progressDialog.dismiss();
                            Toast.makeText(home.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        } else {

                            progressDialog.dismiss();
                            Toast.makeText(home.this, "Couldn,t Register", Toast.LENGTH_LONG).show();

                        }
                    }
                });

    }

    @Override
    public void onClick(View view)
    {
        if(view==buttonRegister)
        {
            registerUser();
        }

        if(view==buttonSignin)
        {
            //will open login activity here
            Intent intent=new Intent(home.this,Main2Activity.class);
            startActivity(intent);

        }
    }
}
