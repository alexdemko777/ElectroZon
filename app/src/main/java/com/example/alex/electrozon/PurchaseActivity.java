package com.example.alex.electrozon;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PurchaseActivity extends AppCompatActivity {
    private ListView productListView;
    private ArrayList<Product> productListCart;
    private ArrayList<Customer> customerList;
    private ArrayAdapter<Product> adapter;
    EditText editFullName;
    EditText editAddress;
    EditText editCity;
    EditText editZip;
    EditText editCardNumber;
    EditText editExpDate;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        editFullName = (EditText) findViewById(R.id.editFullName);
        editAddress = (EditText) findViewById(R.id.editAddress);
        editCity = (EditText) findViewById(R.id.editCity);
        editZip = (EditText) findViewById(R.id.editZip);
        editCardNumber = (EditText) findViewById(R.id.editCardNumber);
        editExpDate = (EditText) findViewById(R.id.editExpDate);
        editExpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editExpDate.setText("");
                DatePickerDialog dateDialog  = new DatePickerDialog(
                        PurchaseActivity.this,
                        datePicker,
                        //first time around show current date
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dateDialog.show();
            }
        });


        Bundle extras = getIntent().getExtras();
        productListCart = extras.getParcelableArrayList("ITEMS");

        adapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, productListCart);
        productListView = (ListView) findViewById(R.id.listViewProductsCartP);
        productListView.setAdapter(adapter);
    }
    DatePickerDialog.OnDateSetListener datePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            Date date = myCalendar.getTime();
            editExpDate.setText(month+1+"-"+dayOfMonth+"-"+year );
        }
    };
    public void btnProcess(View view) {
        Intent intent = new Intent(PurchaseActivity.this, ReceiptActivity.class);
        intent.putParcelableArrayListExtra("ITEMS", productListCart);
        Customer customer = new Customer();
        customer.setFullName(editFullName.getText().toString());
        customer.setAddress(editAddress.getText().toString());
        customer.setCity(editCity.getText().toString());
        customer.setZip(editZip.getText().toString());
        customer.setCardNumber(editCardNumber.getText().toString());
        customer.setExpDate(editExpDate.getText().toString());

        customerList = new ArrayList<Customer>();
        customerList.add(customer);
        intent.putParcelableArrayListExtra("CUSTOMER", customerList);
        startActivity(intent);
    }
}
