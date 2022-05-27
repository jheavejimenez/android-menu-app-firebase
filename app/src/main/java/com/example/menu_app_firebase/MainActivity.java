package com.example.menu_app_firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menu_app_firebase.adapter.BurgerAdapter;
import com.example.menu_app_firebase.listener.IBurgerListener;
import com.example.menu_app_firebase.model.BurgerModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IBurgerListener {

    @BindView(R.id.burgerRecyclerView)
    RecyclerView burgerRecyclerView;

    IBurgerListener BurgerLoadlistener;
    private FirebaseAuth mAuth;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        inititalize();
        loadBurgerList();
    }

    private void inititalize() {
        ButterKnife.bind(this);
        BurgerLoadlistener = this;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        burgerRecyclerView.setLayoutManager(linearLayoutManager);

    }

    private void loadBurgerList() {
        // load burger list from firebase
        List<BurgerModel> burgerModelModelList = new ArrayList<>();
        FirebaseDatabase
                .getInstance()
                .getReference("Burger")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // if snapshot is not exist
                        if (!snapshot.exists()) {
                            BurgerLoadlistener.onBurgerLoadFailed("No data found");
                            return;
                        }
                        // if snapshot is exist
                        for (DataSnapshot burgerSnapshot : snapshot.getChildren()) {
                            BurgerModel burgerModel = burgerSnapshot.getValue(BurgerModel.class);
                            if (burgerModel != null) {
                                burgerModel.setKey(burgerSnapshot.getKey());
                            }
                            burgerModelModelList.add(burgerModel);
                        }
                        BurgerLoadlistener.onBurgerLoadSuccess(burgerModelModelList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        BurgerLoadlistener.onBurgerLoadFailed(error.getMessage());

                    }
                });
    }

    @Override
    public void onBurgerLoadSuccess(List<BurgerModel> burgerModelList) {
        BurgerAdapter burgerAdapter = new BurgerAdapter(this, burgerModelList);
        burgerRecyclerView.setAdapter(burgerAdapter);

    }

    public void onBurgerLoadFailed(String message) {
        // show error message in toast
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}