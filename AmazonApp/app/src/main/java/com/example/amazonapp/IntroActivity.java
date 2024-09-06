package com.example.amazonapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


public class IntroActivity extends AppCompatActivity {
    TextView introSignIn,introSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro);
        introSignIn=findViewById(R.id.signInTextView);
        introSignUp=findViewById(R.id.signUpTextView);

        introSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(IntroActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        introSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(IntroActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        }
    }
