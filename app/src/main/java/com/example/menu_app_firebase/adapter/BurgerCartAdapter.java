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
import com.example.menu_app_firebase.BurgerCartActivity;
import com.example.menu_app_firebase.R;
import com.example.menu_app_firebase.model.BurgerCartModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BurgerCartAdapter extends RecyclerView.Adapter<BurgerCartAdapter.BurgerCartViewHolder> {

    private List<BurgerCartModel> burgerCartItems;
    private Context context;

    public BurgerCartAdapter(List<BurgerCartModel> burgerCartItems, Context context) {
        this.burgerCartItems = burgerCartItems;
        this.context = context;
    }

    @NonNull
    @Override
    public BurgerCartAdapter.BurgerCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BurgerCartAdapter.BurgerCartViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_add_to_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BurgerCartViewHolder holder, int position) {
        Glide.with(context).load(burgerCartItems.get(position).getImage()).into(holder.burgerImage);
        holder.burgerName.setText(burgerCartItems.get(position).getName());
        holder.burgerPrice.setText(burgerCartItems.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return burgerCartItems.size();
    }

    public class BurgerCartViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.burgerImage2)
        ImageView burgerImage;
        @BindView(R.id.burgerName2)
        TextView burgerName;
        @BindView(R.id.price2)
        TextView burgerPrice;

//        @BindView(R.id.total)
//        TextView totalPrice;

        Unbinder unbinder;
        public BurgerCartViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }

    }
}