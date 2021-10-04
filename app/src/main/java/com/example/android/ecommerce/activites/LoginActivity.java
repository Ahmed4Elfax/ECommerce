package com.example.android.ecommerce.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.ecommerce.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText  email , password ;
    private FirebaseAuth auth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
    }
    public void signin(View view){
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();


        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this , "Enter Email Address !!" ,Toast.LENGTH_SHORT);
            return;
        }
        if(TextUtils.isEmpty(userPassword)){
            Toast.makeText(this , "Enter Password !!" ,Toast.LENGTH_SHORT);
            return;
        }
        if(userPassword.length() < 8 ){
            Toast.makeText(this , "Password is very short , the password should be at least 8 characters ." ,Toast.LENGTH_LONG);
            return;
        }
        auth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this , "Login Successful !1" ,Toast.LENGTH_SHORT);
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }else{
                            Toast.makeText(LoginActivity.this , "Error  !!" ,Toast.LENGTH_SHORT);
                        }
                    }
                });
    }
    public void signup(View view){
        startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
    }
}