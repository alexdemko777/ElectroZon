package com.example.alex.electrozon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {
    private ArrayList<Product> productList;
    private int productIndex;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        info = (TextView) findViewById(R.id.info);

        Bundle extras = getIntent().getExtras();
        productList = extras.getParcelableArrayList("ITEMS");
        productIndex = getIntent().getExtras().getInt("index");

        info.setText(productList.get(productIndex).getproductDesc());


    }
}
