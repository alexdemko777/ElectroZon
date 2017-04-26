package com.example.alex.electrozon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private ListView productListView;
    private ArrayList<Product> productList;
    private ArrayList<Product> productListCart;
    private ArrayAdapter<Product> adapter;
    private int productIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Bundle extras = getIntent().getExtras();
        productList = extras.getParcelableArrayList("ITEMS");

        productIndex = extras.getInt("index");

        Product product = new Product();
        product.setProductId(productList.get(productIndex).getProductId());
        product.setProductName(productList.get(productIndex).getProductName());
        product.setPrice(productList.get(productIndex).getPrice());

        productListCart = new ArrayList<Product>();
        productListCart.add(product);

        adapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, productListCart);
        productListView = (ListView) findViewById(R.id.listViewProductsCart);
        productListView.setAdapter(adapter);

    }
    public void btnBuy(View view) {
        Intent intent = new Intent(CartActivity.this, PurchaseActivity.class);
        startActivity(intent);
    }
}
