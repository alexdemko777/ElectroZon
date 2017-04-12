package com.example.alex.electrozon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PurchaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
    }
    public void btnProcess(View view) {
        Intent intent = new Intent(PurchaseActivity.this, ReceiptActivity.class);
        startActivity(intent);
    }
}
