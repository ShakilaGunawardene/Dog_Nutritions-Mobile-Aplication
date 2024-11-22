package com.example.fidof;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class SignUpPage extends AppCompatActivity {
    EditText signnupName, signupEmail, signupMobile, signupPassowrd;
    Button Register;
    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_page);


        signupEmail = findViewById(R.id.signup_email);
        signnupName = findViewById(R.id.signup_username);
        signupPassowrd = findViewById(R.id.signup_password);
        signupMobile = findViewById(R.id.signup_phone);
        Register = findViewById(R.id.signup_button);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");


                String username = signnupName.getText().toString();
                String email = signupEmail.getText().toString();
                String password = signupPassowrd.getText().toString();
                String phoneNumber = signupMobile.getText().toString();

                Helper helper = new Helper(email, password, phoneNumber, username);
                reference.child(username).setValue(helper);

                Toast.makeText(SignUpPage.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpPage.this, Home.class);
                startActivity(intent);

            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}