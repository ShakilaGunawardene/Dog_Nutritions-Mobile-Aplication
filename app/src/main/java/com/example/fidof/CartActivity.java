package com.example.fidof;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private ListView cartListView;
    private TextView totalPriceTextView;
    private ArrayAdapter<Product> cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartListView = findViewById(R.id.cart_list);
        totalPriceTextView = findViewById(R.id.total_price);

        List<Product> cartProducts = Cart.getInstance().getProducts();
        cartAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cartProducts);
        cartListView.setAdapter(cartAdapter);

        updateTotalPrice();
    }

    private void updateTotalPrice() {
        double totalPrice = Cart.getInstance().getTotalPrice();
        Log.d("CartActivity", "Total Price: " + totalPrice); // Log the total price
        totalPriceTextView.setText(String.format("Total Price: $%.2f", totalPrice));
    }

}
