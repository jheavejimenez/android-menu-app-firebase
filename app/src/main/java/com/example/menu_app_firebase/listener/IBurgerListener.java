package com.example.menu_app_firebase.listener;

import com.example.menu_app_firebase.model.BurgerModel;

import java.util.List;

public interface IBurgerListener {
    void onBurgerLoadSuccess(List<BurgerModel> burgerModelList);
    void onBurgerLoadFailed(String message);
}
