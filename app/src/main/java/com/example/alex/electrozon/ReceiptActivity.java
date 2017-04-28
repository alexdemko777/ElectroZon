package com.example.alex.electrozon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ReceiptActivity extends AppCompatActivity {
    private ListView productListView;
    private ListView productListView2;
    private ArrayList<Product> productListCart;
    private ArrayList<Customer> customerList;
    private ArrayAdapter<Product> adapter;
    private ArrayAdapter<Customer> adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        Bundle extras = getIntent().getExtras();
        productListCart = extras.getParcelableArrayList("ITEMS");
        customerList = extras.getParcelableArrayList("CUSTOMER");

        adapter2 = new ArrayAdapter<Customer>(this, android.R.layout.simple_list_item_1, customerList);
        productListView2 = (ListView) findViewById(R.id.listViewInfo);
        productListView2.setAdapter(adapter2);

        adapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, productListCart);
        productListView = (ListView) findViewById(R.id.listViewProductsCartR);
        productListView.setAdapter(adapter);
    }
    public void btnShopAgain(View view) {
        Intent intent = new Intent(ReceiptActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
