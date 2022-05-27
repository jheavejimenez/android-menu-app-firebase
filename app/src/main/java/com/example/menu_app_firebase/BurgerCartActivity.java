package com.example.menu_app_firebase;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menu_app_firebase.adapter.BurgerCartAdapter;
import com.example.menu_app_firebase.model.BurgerCartModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BurgerCartActivity extends AppCompatActivity {

    @BindView(R.id.burgerCartRecycler)
    RecyclerView burgerCartRecycler;

    private List<BurgerCartModel> burgerCartModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burger_cart);

        inititalize();
    }

    private void inititalize() {
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        burgerCartRecycler.setLayoutManager(linearLayoutManager);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("add_to_cart");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        BurgerCartModel burgerCartModel = dataSnapshot.getValue(BurgerCartModel.class);
                        burgerCartModelList.add(burgerCartModel);
                    }
                    // TODO: Add total price Computation
                    burgerCartRecycler.setAdapter(new BurgerCartAdapter(burgerCartModelList, BurgerCartActivity.this));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Toast.makeText(BurgerCartActivity.this, "Failed to read value", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
