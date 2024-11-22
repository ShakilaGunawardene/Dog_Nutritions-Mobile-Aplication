package com.example.fidof;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ProductAdaptor extends ArrayAdapter<Product> {
    private Context context;
    private List<Product> products;
    private DatabaseReference databaseReference;

    public ProductAdaptor(Context context, List<Product> products) {
        super(context, 0, products);
        this.context = context;
        this.products = products;
        databaseReference = FirebaseDatabase.getInstance().getReference("products"); // Assuming your products node is named "products"
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        }

        Product product = products.get(position);

        ImageView productImageView = convertView.findViewById(R.id.product_image);
        TextView nameTextView = convertView.findViewById(R.id.product_name);
        TextView priceTextView = convertView.findViewById(R.id.product_price);

        nameTextView.setText(product.getProductName());
        priceTextView.setText(String.format("$%.2f", product.getProductPrice()));

        new ImageLoadTask(product.getImageURL(), productImageView).execute();

        return convertView;
    }

    private static class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {
        private String url;
        private ImageView imageView;

        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                return BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (result != null) {
                imageView.setImageBitmap(result);
            }
        }
    }

    public void loadProducts() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                products.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Product product = snapshot.getValue(Product.class);
                    products.add(product);
                }
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }
}
