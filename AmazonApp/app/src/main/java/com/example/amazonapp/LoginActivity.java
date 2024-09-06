package com.example.amazonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText emailEditText, passwordEditText;
    AppCompatButton signInButton;
    LinearLayout signUpText;
    String emailPattern= "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth=FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);

        emailEditText=findViewById(R.id.loginEmail);
        passwordEditText=findViewById(R.id.loginPassword);
        signInButton=findViewById(R.id.signInButton);
        signInButton.setBackgroundResource(R.drawable.intro_signin);

        signUpText=findViewById(R.id.signUpText);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                String email= emailEditText.getEditableText().toString();
                String password=passwordEditText.getEditableText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Enter valid data", Toast.LENGTH_SHORT).show();
                }else if(!email.matches(emailPattern)){
                    progressDialog.dismiss();
                    emailEditText.setError("Invalid Email");
                    Toast.makeText(LoginActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }else if(password.length()<=6){
                    progressDialog.dismiss();
                    passwordEditText.setError("Invalid password");
                    Toast.makeText(LoginActivity.this, "Please enter more than 6 characters", Toast.LENGTH_SHORT).show();
                }else{
                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Error in login. Try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}