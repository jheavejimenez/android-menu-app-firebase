package com.example.menu_app_firebase.listener;

import com.example.menu_app_firebase.model.BurgerModel;

import java.util.List;

public interface IBurgerDetailsListener {
    void onBurgerDetailsSuccess(List<BurgerModel> burgerModelList);
    void onBurgerDetailsFailure(String message);
}
