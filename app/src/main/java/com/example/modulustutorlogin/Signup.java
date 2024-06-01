package com.example.modulustutorlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    private FirebaseAuth autn;
    private EditText signup_email,sign_uppassword;
    private Button sign_upbutton;
    private TextView signbot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        autn = FirebaseAuth.getInstance();
        signup_email = findViewById(R.id.signup_email);
        sign_uppassword = findViewById(R.id.sign_uppassword);
        sign_upbutton = findViewById(R.id.sign_upbutton);
        signbot =findViewById(R.id.signbot);

        sign_upbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = signup_email.getText().toString().trim();
                String password = sign_uppassword.getText().toString().trim();

                if(user.isEmpty()){
                    signup_email.setError("Email Cannot Be Empty");
                }
                if(password.isEmpty()){
                    sign_uppassword.setError("Password Cannot Be Empty");
                }
                autn.createUserWithEmailAndPassword(user,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Signup.this,"Sign Up Succsesfull",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Signup.this, Login.class));

                        }
                        else{
                            Toast.makeText(Signup.this,"Sign Up Failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        signbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this,Login.class));
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}