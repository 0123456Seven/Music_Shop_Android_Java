package com.xaero.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int quantity=0;
    private double price;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    String goodsName;
    EditText userNameEditText;






    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.nameEditText);

        createSpinner();

        createMap();
    }
    public void createSpinner(){
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();

        spinnerArrayList.add("guitar");
        spinnerArrayList.add("drums");
        spinnerArrayList.add("keyboard");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    public void createMap(){
        goodsMap  = new HashMap();
        goodsMap.put("guitar", 1000.0);
        goodsMap.put("drums", 1500.0);
        goodsMap.put("keyboard",500.0);
    }

    public void plusQuantityCount(View view) {
        TextView textViewQuantityNumber = findViewById(R.id.text_quantity_number);
        quantity += 1;
        textViewQuantityNumber.setText(""+quantity);
        TextView priceTextView = findViewById(R.id.text_order_price_number);
        priceTextView.setText(""+quantity*price);
    }

    public void minusQuantityCount(View view) {
        if (quantity >0){
            TextView textViewQuantityNumber = findViewById(R.id.text_quantity_number);
            quantity -= 1;
            textViewQuantityNumber.setText(""+quantity);
            TextView priceTextView = findViewById(R.id.text_order_price_number);
            priceTextView.setText(""+quantity*price);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double)goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.text_order_price_number);
        priceTextView.setText(""+quantity*price);
        ImageView goodsImageView = findViewById(R.id.goodsImageView);
        switch(goodsName){
            case"guitar":
                goodsImageView.setImageResource(R.drawable.guitar2);
                break;
            case"drums":
                goodsImageView.setImageResource(R.drawable.drums);
                break;
            case"keyboard":
                goodsImageView.setImageResource(R.drawable.keyboard);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.price = price;
        order.orderPrice = quantity*price;
        Log.d("userName:", order.userName);
        Log.d("goodsName:", order.goodsName);
        Log.d("quantity:", String.valueOf(order.quantity));
        Log.d("orderPrice:", String.valueOf(order.orderPrice));

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userNameForIntent", order.userName);
        orderIntent.putExtra("goodsNameForIntent", order.goodsName);
        orderIntent.putExtra("quantityForIntent", order.quantity);
        orderIntent.putExtra("priceForIntent", order.price);
        orderIntent.putExtra("orderPriceForIntent", order.orderPrice);
        startActivity(orderIntent);

    }
}