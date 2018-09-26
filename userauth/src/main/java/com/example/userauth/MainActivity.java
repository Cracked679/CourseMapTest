package com.example.userauth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "LogIn";

    //widgets
    private Button log_mLogin,log_mSignup;
    private EditText log_editTextEmail,log_editTextPassword;
    private ProgressBar log_progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        log_editTextEmail = (EditText) findViewById(R.id.log_editTextLogEmail);
        log_editTextPassword = (EditText) findViewById(R.id.log_editTextLogPassword);
        log_progressBar = (ProgressBar) findViewById(R.id.log_progressbar);
        log_mSignup = (Button)findViewById(R.id.log_signup);
        log_mLogin = (Button)findViewById(R.id.log_login);

        log_mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

        log_mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

    }

    private void signUp() {
        Intent signup_activity = new Intent(MainActivity.this,SignUpActivity.class);
        startActivity(signup_activity);
    }


    private void userLogin() {
        String email = log_editTextEmail.getText().toString().trim();
        String password = log_editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            log_editTextEmail.setError("Email is required");
            log_editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            log_editTextEmail.setError("Please enter valid email");
            log_editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            log_editTextPassword.setError("Password is required");
            log_editTextPassword.requestFocus();
            return;
        }

        if (password.length()<6){
            log_editTextPassword.setError("Minimum length of password must be 6");
            log_editTextPassword.requestFocus();
            return;
        }

        log_progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                log_progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    Intent login_activity = new Intent(MainActivity.this,ProfileActivity.class);
                    login_activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(login_activity);

                }
                else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onClick(View view) {

    }
}
