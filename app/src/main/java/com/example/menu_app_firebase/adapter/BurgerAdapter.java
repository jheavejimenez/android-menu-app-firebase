package com.example.menu_app_firebase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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

        private Unbinder unbinder;
        public BurgerViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }
    }
}
