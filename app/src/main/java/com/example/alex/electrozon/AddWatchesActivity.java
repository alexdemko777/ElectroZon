package com.example.alex.electrozon;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import static java.sql.Types.NULL;

public class AddWatchesActivity extends AppCompatActivity {
    EditText productName;
    EditText productDesc;
    EditText qty;
    EditText price;
    SQLiteDatabase dbHomePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_watches);
        productName = (EditText) findViewById(R.id.editProductW);
        productDesc = (EditText) findViewById(R.id.editDescW);
        price = (EditText) findViewById(R.id.editPriceW);
        qty = (EditText) findViewById(R.id.editQtyW);
        openDatabase();
        createProductTable();
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
    // create new database table function
    private void createProductTable() {
        dbHomePage.beginTransaction();
        try {
            String myQuery = "CREATE TABLE IF NOT EXISTS watchesproduct1 (\n" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                    "product_name TEXT DEFAULT NULL, \n" +
                    "product_desc TEXT DEFAULT NULL, \n" +
                    "qty INTEGER NOT NULL, \n" +
                    "price INTEGER NOT NULL);";
            dbHomePage.execSQL(myQuery);
            dbHomePage.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.i("message", "Error createTable(): "+e.getMessage());
            finish();
        } finally {
            dbHomePage.endTransaction();
        }
    }
    // add new data to our database
    private void insertDataToDbTable(Product product) {
        ContentValues cv = new ContentValues();
        cv.put("product_name", product.getProductName());
        cv.put("product_desc", product.getproductDesc());
        cv.put("qty", product.getQty());
        cv.put("price", product.getPrice());
        dbHomePage.insert("watchesproduct1", null, cv);
        cv.clear();
    }
    // clear all fields
    public void clearEditTextFields() {
        productName.setText("");
        qty.setText("");
        price.setText("");
    }
    public void btnAddProductW(View view) {
        if(!productName.getText().toString().equals("") &&
                !qty.getText().toString().equals("") &&
                !price.getText().toString().equals(""))
        {
            Product product = new Product(NULL, productName.getText().toString(),
                    productDesc.getText().toString(),
                    Long.valueOf(qty.getText().toString()),
                    Long.valueOf(price.getText().toString()));
            insertDataToDbTable(product);
            clearEditTextFields();
            Intent intent = new Intent(AddWatchesActivity.this, WatchesActivity.class);
            startActivity(intent);
        }
    }
}
