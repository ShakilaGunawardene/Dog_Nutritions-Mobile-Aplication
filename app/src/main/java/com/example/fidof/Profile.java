package com.example.fidof;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    TextView profileName, profileEmail, profilePhone, profilePassword;
    TextView textName, textEmail;
    Button updateProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        profileName = findViewById(R.id.profileName_view);
        profileEmail = findViewById(R.id.profileEmail_view);
        profilePhone = findViewById(R.id.profilePhone_view);
        profilePassword = findViewById(R.id.profilePassword_view);
        textName = findViewById(R.id.profileName_up);
        textEmail = findViewById(R.id.profileEmail_up);
        updateProfile = findViewById(R.id.update_button);

        showData();

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passUserData();
            }
        });
    }

    public void showData() {
        Intent intent = getIntent();

        String nameUser = intent.getStringExtra("username");
        String emailUser = intent.getStringExtra("email");
        String phoneUser = intent.getStringExtra("phoneNumber");
        String passwordUser = intent.getStringExtra("password");

        textName.setText(nameUser);
        textEmail.setText(emailUser);
        profileName.setText(nameUser);
        profileEmail.setText(emailUser);
        profilePhone.setText(phoneUser);
        profilePassword.setText(passwordUser);
    }

    public void passUserData() {
        String userEmail = profileEmail.getText().toString().trim();
        String sanitizedEmail = sanitizePath(userEmail);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("email").equalTo(userEmail);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        String nameFromDB = userSnapshot.child("username").getValue(String.class);
                        String emailFromDB = userSnapshot.child("email").getValue(String.class);
                        String phoneFromDB = userSnapshot.child("phoneNumber").getValue(String.class);
                        String passwordFromDB = userSnapshot.child("password").getValue(String.class);

                        Intent intent = new Intent(Profile.this, EditProfile.class);
                        intent.putExtra("username", nameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("phoneNumber", phoneFromDB);
                        intent.putExtra("password", passwordFromDB);
                        startActivity(intent);
                        return;
                    }
                } else {
                    Log.d("Profile", "No matching user found in the database.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Profile", "Database error: " + error.getMessage());
            }
        });
    }

    private String sanitizePath(String path) {
        return path.replace(".", ",").replace("#", ",").replace("$", ",").replace("[", ",").replace("]", ",");
    }
}
