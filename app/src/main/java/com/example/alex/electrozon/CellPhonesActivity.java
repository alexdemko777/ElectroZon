package com.example.alex.electrozon;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CellPhonesActivity extends AppCompatActivity {
    ListView productListView;
    SQLiteDatabase dbHomePage;
    ArrayList<Product> productList;
    ArrayAdapter<Product> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell_phones);
        productList = new ArrayList<Product>();
        productListView = (ListView) findViewById(R.id.listViewProducts);
        adapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, productList);
        productListView.setAdapter(adapter);
        openDatabase();
        getProducts();
    }
    // open database function, database name is electrozondata in sqlite
    private void openDatabase(){
        try {
            dbHomePage = openOrCreateDatabase("electrozondata", MODE_PRIVATE, null);
        } catch (SQLiteException e){
            Log.i("message", "openDatabase(): " + e.getMessage());
            finish();
        }
    }
    // get product from the database function
    private void getProducts() {
        Product tempProduct;
        productList.clear();
        try {
            Cursor cr = dbHomePage.rawQuery("SELECT * FROM cellphonesproduct;", null);
            if (cr != null) {
                while (cr.moveToNext()) {
                    tempProduct = new Product();
                    tempProduct.setProductId(cr.getInt(cr.getColumnIndex("id")));
                    tempProduct.setProductName(cr.getString(cr.getColumnIndex("product_name")));
                    tempProduct.setQty(cr.getLong(cr.getColumnIndex("qty")));
                    tempProduct.setPrice(cr.getLong(cr.getColumnIndex("price")));
                    productList.add(tempProduct);
                }
                adapter.notifyDataSetChanged();
            }
            cr.close();
        } catch (Exception e) {
            Log.i("message", "Error getProducts(): " + e.getMessage());
        }
    }
    // action bar functions for the homepage
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    // action bar navigation buttons
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_add){
            Intent intent = new Intent(CellPhonesActivity.this, AddCellPhonesActivity.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId()==R.id.action_cart){
            Intent intent = new Intent(CellPhonesActivity.this, CartActivity.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId()==R.id.action_settings){
            Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
