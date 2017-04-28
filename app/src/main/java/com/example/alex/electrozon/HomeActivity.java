package com.example.alex.electrozon;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private ListView productListView;
    private SQLiteDatabase dbHomePage;
    private ArrayList<Product> productList;
    private ArrayList<Product> productListCart;
    private ArrayAdapter<Product> adapter;
    private int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        productList = new ArrayList<Product>();
        adapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, productList);
        productListView = (ListView) findViewById(R.id.listViewProducts);
        productListView.setAdapter(adapter);
        // register for popup menu
        registerForContextMenu(productListView);
        // open database and get products
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
            Cursor cr = dbHomePage.rawQuery("SELECT * FROM homeproduct1;", null);
            if (cr != null) {
                while (cr.moveToNext()) {
                    tempProduct = new Product();
                    tempProduct.setProductId(cr.getInt(cr.getColumnIndex("id")));
                    tempProduct.setProductName(cr.getString(cr.getColumnIndex("product_name")));
                    tempProduct.setproductDesc(cr.getString(cr.getColumnIndex("product_desc")));
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_add){
            Intent intent = new Intent(HomeActivity.this, AddHomeActivity.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId()==R.id.action_cart){
            Toast.makeText(this, "Please press and hold an item to add to cart", Toast.LENGTH_LONG).show();
            return true;
        }
        if(item.getItemId()==R.id.action_settings){
            Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // Create my popup
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup, menu);
    }
    // add to cart, view description function
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId()==R.id.action_AddToCart){
            addToCartItem(info.position);
            return true;
        }
        if(item.getItemId()==R.id.action_info){
            viewInfo(info.position);
            return true;
        }
        return super.onContextItemSelected(item);
    }
    // Add to cart function and pass it to another activity
    public void addToCartItem(int index) {
        Intent intent = new Intent(HomeActivity.this, CartActivity.class);
        intent.putParcelableArrayListExtra("ITEMS", productList);
        intent.putExtra("ID", productId);
        intent.putExtra("index", index);
        //intent.putParcelableArrayListExtra("ITEMSc", productListCart);
        startActivity(intent);
    }

    // View Info
    public void viewInfo(int index) {
        Intent intent = new Intent(HomeActivity.this, InfoActivity.class);
        intent.putParcelableArrayListExtra("ITEMS", productList);
        //intent.putExtra("ID", productId);
        intent.putExtra("index", index);
        startActivity(intent);
    }

    public void showPopup(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.getMenuInflater().inflate(R.menu.category, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.action_computers){
                    Intent intent = new Intent(HomeActivity.this, ComputersActivity.class);
                    startActivity(intent);
                    return true;
                }
                if(item.getItemId()==R.id.action_tablets){
                    Intent intent = new Intent(HomeActivity.this, TabletsActivity.class);
                    startActivity(intent);
                    return true;
                }
                if(item.getItemId()==R.id.action_cellphones){
                    Intent intent = new Intent(HomeActivity.this, CellPhonesActivity.class);
                    startActivity(intent);
                    return true;
                }
                if(item.getItemId()==R.id.action_watches){
                    Intent intent = new Intent(HomeActivity.this, WatchesActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
        popup.show();
    }
}
