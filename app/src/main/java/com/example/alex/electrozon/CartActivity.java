package com.example.alex.electrozon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
    }
    public void btnBuy(View view) {
        Intent intent = new Intent(CartActivity.this, PurchaseActivity.class);
        startActivity(intent);
    }
}