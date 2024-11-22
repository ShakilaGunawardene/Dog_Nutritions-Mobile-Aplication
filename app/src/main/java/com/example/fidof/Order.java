package com.example.fidof;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Order extends AppCompatActivity {
    Button OrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order);

        OrderButton = findViewById(R.id.order_button);

        OrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Order.this, "Order Confirm", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Order.this, Cart.class);
                startActivity(intent);

            }
        });


    }
}