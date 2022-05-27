package com.example.menu_app_firebase;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.menu_app_firebase.listener.IBurgerDetailsListener;

import butterknife.ButterKnife;

public class BurgerDetailsActivity extends AppCompatActivity {

    IBurgerDetailsListener burgerLoadDetailsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_item_details);
        ButterKnife.bind(this);
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
