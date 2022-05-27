package com.example.menu_app_firebase.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.menu_app_firebase.BurgerDetailsActivity;
import com.example.menu_app_firebase.R;
import com.example.menu_app_firebase.model.BurgerModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BurgerAdapter extends RecyclerView.Adapter<BurgerAdapter.BurgerViewHolder> {

    private final List<BurgerModel> burgerModelList;
    private final Context context;

    public BurgerAdapter(Context context, List<BurgerModel> burgerModelList) {
        this.burgerModelList = burgerModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public BurgerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BurgerViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_burger_display, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BurgerViewHolder holder, int position) {
        Glide.with(context).load(burgerModelList.get(position).getImage()).into(holder.burgerImage);
        holder.burgerName.setText(burgerModelList.get(position).getName());
        holder.amountOfCalories.setText(burgerModelList.get(position).getCalories());
        holder.burgerPrice.setText(burgerModelList.get(position).getPrice());
        holder.mainLayout.setOnClickListener(v -> {
           holder.burgerImage.setTag(burgerModelList.get(position).getImage());
           Intent intent = new Intent(context, BurgerDetailsActivity.class);
           intent.putExtra("image", burgerModelList.get(position).getImage());
           intent.putExtra("burger_name", burgerModelList.get(position).getName());
           intent.putExtra("description", burgerModelList.get(position).getDescription());
           intent.putExtra("burger_calories", burgerModelList.get(position).getCalories());
           intent.putExtra("burger_price", burgerModelList.get(position).getPrice());

           context.startActivity(intent);

        });


    }

    @Override
    public int getItemCount() {
        return burgerModelList.size();
    }

    public class BurgerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.burgerImage)
        ImageView burgerImage;
        @BindView(R.id.burgerName)
        TextView burgerName;
        @BindView(R.id.amountOfCalories)
        TextView amountOfCalories;
        @BindView(R.id.burgerPrice)
        TextView burgerPrice;

        @BindView(R.id.activity_main)
        ConstraintLayout mainLayout;

        Unbinder unbinder;

        public BurgerViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }
    }

}
