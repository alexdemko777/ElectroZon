package com.example.alex.electrozon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ReceiptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
    }
    public void btnShopAgain(View view) {
        Intent intent = new Intent(ReceiptActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
