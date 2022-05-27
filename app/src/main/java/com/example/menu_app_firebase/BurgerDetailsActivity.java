package com.example.menu_app_firebase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.menu_app_firebase.listener.IBurgerDetailsListener;

import butterknife.ButterKnife;

public class BurgerDetailsActivity extends AppCompatActivity {

    IBurgerDetailsListener burgerLoadDetailsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        ButterKnife.bind(this);
        setUpViews();

    }

    private void setUpViews() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String name = bundle.getString("name");
            String description = bundle.getString("description");
            String calories = bundle.getString("calories");
            String price = bundle.getString("price");

            TextView nameTextView = findViewById(R.id.itemHeadline);
            TextView descriptionTextView = findViewById(R.id.itemDescription);
            TextView priceTextView = findViewById(R.id.itemPrice);
            TextView caloriesTextView = findViewById(R.id.itemCalories);

            nameTextView.setText(name);
            priceTextView.setText(price);
            caloriesTextView.setText(calories);
            descriptionTextView.setText(description);
        }
    }


}
