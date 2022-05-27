package com.example.menu_app_firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import butterknife.ButterKnife;

public class BurgerDetailsActivity extends AppCompatActivity {

    Button addToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_item_details);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("add_to_cart");
        ButterKnife.bind(this);
        addToCart = findViewById(R.id.itemAddToCart);

        String idCart = mDatabase.push().getKey();
        addToCart.setOnClickListener(v -> {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                String imageUrl = bundle.getString("image");
                String name = bundle.getString("burger_name");
                String price = bundle.getString("burger_price");

                HashMap<String, Object> cartItem = new HashMap<>();
                cartItem.put("image", imageUrl);
                cartItem.put("name", name);
                cartItem.put("price", price);

                mDatabase.child(idCart).setValue(cartItem);
            }

            startActivity(new Intent(BurgerDetailsActivity.this, BurgerCartActivity.class));
        });

        setUpViews();


    }

    private void setUpViews() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            String imageUrl = bundle.getString("image");
            String name = bundle.getString("burger_name");
            String description = bundle.getString("description");
            String calories = bundle.getString("burger_calories");
            String price = bundle.getString("burger_price");

            ImageView imageView = findViewById(R.id.itemImage);

            TextView nameTextView = findViewById(R.id.itemHeadline);
            TextView descriptionTextView = findViewById(R.id.itemDescription);
            TextView priceTextView = findViewById(R.id.itemPrice);
            TextView caloriesTextView = findViewById(R.id.itemCalories);

            // set the image using URL
            Glide.with(this).load(imageUrl).into(imageView);

            nameTextView.setText(name);
            priceTextView.setText(price);
            caloriesTextView.setText(calories);
            descriptionTextView.setText(description);
        }
    }


}
