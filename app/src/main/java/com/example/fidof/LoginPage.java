package com.example.fidof;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {
    EditText loginEmail, loginPassword;
    Button loginButton;
    TextView SignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);

        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        SignUp = findViewById(R.id.signupredirecttext);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validEmail()  | !validpassword()){

                }else {
                    checkUser();
                }
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, SignUpPage.class);
                startActivity(intent);
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public boolean validEmail(){
        String val = loginEmail.getText().toString();
        if(val.isEmpty()){
            loginEmail.setError("Email Can't be Empty !");
            return false;
        }else {
            loginEmail.setError(null);
            return true;
        }

    }
    public boolean validpassword(){
        String val = loginPassword.getText().toString();
        if(val.isEmpty()){
            loginPassword.setError("Password Can't be Empty !");
            return false;
        }else {
            loginPassword.setError(null);
            return true;
        }

    }
    public void checkUser(){
        String userEmail = loginEmail.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();

        if (userEmail.equals("admin@gmail.com") && userPassword.equals("123")) {
            Intent intent = new Intent(LoginPage.this, AdminPage.class);
            startActivity(intent);
            return;
        }


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserdatabase = reference.orderByChild("email").equalTo(userEmail);

        checkUserdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {

                        String passwordFromDB = userSnapshot.child("password").getValue(String.class);

                        if (passwordFromDB != null && passwordFromDB.equals(userPassword)) {

                            loginEmail.setError(null);

                            Intent intent = new Intent(LoginPage.this, Home.class);

                            startActivity(intent);

                            return;

                        } else {

                            loginPassword.setError("Invalid Credentials");

                            loginPassword.requestFocus();

                            return;

                        }

                    }

                } else {

                    loginEmail.setError("No user found with this email");

                    loginEmail.requestFocus();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}