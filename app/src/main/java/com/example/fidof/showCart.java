package com.example.fidof;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class showCart extends AppCompatActivity {
    private ListView productListView;
    private List<Product> productList;
    private ProductAdaptor productAdapter;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_cart);

        productListView = findViewById(R.id.product_list);
        productList = new ArrayList<>();
        productAdapter = new ProductAdaptor(this, productList);
        productListView.setAdapter(productAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Product");

        // Fetch products from Realtime Database
        fetchProductsFromDatabase();

        // Set a button to go to the cart
        Button viewCartButton = findViewById(R.id.view_cart_button);
        viewCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(showCart.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void fetchProductsFromDatabase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Product product = snapshot.getValue(Product.class);
                    if (product != null) {
                        productList.add(product);
                        Log.d("showCart", "Product added: " + product.getProductName() + " - $" + product.getProductPrice());
                    }
                }
                productAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(showCart.this, "Error getting products", Toast.LENGTH_SHORT).show();
            }
        });
    }

}