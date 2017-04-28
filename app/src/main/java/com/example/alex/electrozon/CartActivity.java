package com.example.alex.electrozon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        //productListCart = extras.getParcelableArrayList("ITEMSc");

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_home){
            Intent intent = new Intent(CartActivity.this, HomeActivity.class);
            //intent.putParcelableArrayListExtra("ITEMS", productListCart);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.action_settings){
            Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void btnBuy(View view) {
        Intent intent = new Intent(CartActivity.this, PurchaseActivity.class);
        intent.putParcelableArrayListExtra("ITEMS", productListCart);
        startActivity(intent);
    }
}
