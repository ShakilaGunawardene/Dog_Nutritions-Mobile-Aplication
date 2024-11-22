package com.example.fidof;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends AppCompatActivity {
    private static final String TAG = "EditProfile";
    EditText editName, editEmail, editPhone, editPassword;
    Button saveButton;
    String nameUser, emailUser, usernameUser, passwordUser, phoneUser;
    TextView textName, textEmail;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);

        reference = FirebaseDatabase.getInstance().getReference("users");

        editName = findViewById(R.id.profileName_edit);
        editEmail = findViewById(R.id.profileEmail_edit);
        editPhone = findViewById(R.id.profilePhone_edit);
        editPassword = findViewById(R.id.profilePassword_edit);
        textName = findViewById(R.id.profileName_up);
        textEmail = findViewById(R.id.profileEmail_up);
        saveButton = findViewById(R.id.save_button);

        showData();
        saveButton.setOnClickListener(v -> {
            if (isNameChange() || isEmailChange() || isPhoneNumberChange() || isPasswordChange()) {
                Toast.makeText(EditProfile.this, "Saved", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(EditProfile.this, "No changes found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean isNameChange() {
        String newName = editName.getText().toString();
        if (nameUser != null && !nameUser.equals(newName)) {
            reference.child(sanitizePath(emailUser)).child("username").setValue(newName)
                    .addOnSuccessListener(aVoid -> Log.d(TAG, "Username updated successfully"))
                    .addOnFailureListener(e -> Log.e(TAG, "Failed to update username", e));
            nameUser = newName;
            return true;
        }
        return false;
    }

    public boolean isEmailChange() {
        String newEmail = editEmail.getText().toString();
        if (emailUser != null && !emailUser.equals(newEmail)) {
            reference.child(sanitizePath(emailUser)).child("email").setValue(newEmail)
                    .addOnSuccessListener(aVoid -> Log.d(TAG, "Email updated successfully"))
                    .addOnFailureListener(e -> Log.e(TAG, "Failed to update email", e));
            emailUser = newEmail;
            return true;
        }
        return false;
    }

    public boolean isPhoneNumberChange() {
        String newPhone = editPhone.getText().toString();
        if (phoneUser != null && !phoneUser.equals(newPhone)) {
            reference.child(sanitizePath(emailUser)).child("phoneNumber").setValue(newPhone)
                    .addOnSuccessListener(aVoid -> Log.d(TAG, "Phone number updated successfully"))
                    .addOnFailureListener(e -> Log.e(TAG, "Failed to update phone number", e));
            phoneUser = newPhone;
            return true;
        }
        return false;
    }

    public boolean isPasswordChange() {
        String newPassword = editPassword.getText().toString();
        if (passwordUser != null && !passwordUser.equals(newPassword)) {
            reference.child(sanitizePath(emailUser)).child("password").setValue(newPassword)
                    .addOnSuccessListener(aVoid -> Log.d(TAG, "Password updated successfully"))
                    .addOnFailureListener(e -> Log.e(TAG, "Failed to update password", e));
            passwordUser = newPassword;
            return true;
        }
        return false;
    }

    public void showData() {
        nameUser = getIntent().getStringExtra("username");
        emailUser = getIntent().getStringExtra("email");
        phoneUser = getIntent().getStringExtra("phoneNumber");
        passwordUser = getIntent().getStringExtra("password");

        textName.setText(nameUser);
        textEmail.setText(emailUser);
        editName.setText(nameUser);
        editEmail.setText(emailUser);
        editPhone.setText(phoneUser);
        editPassword.setText(passwordUser);
    }

    private String sanitizePath(String path) {
        return path.replace(".", ",").replace("#", ",").replace("$", ",").replace("[", ",").replace("]", ",");
    }
}
