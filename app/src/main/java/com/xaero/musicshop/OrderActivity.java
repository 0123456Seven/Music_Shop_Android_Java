package com.xaero.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    String[] addresses= {"mefefef@bk.ru"};
    String subject = "Order from Music Shop";
    String emailText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("userNameForIntent");
        String goodsName = receivedOrderIntent.getStringExtra("goodsNameForIntent");
        int quantity = receivedOrderIntent.getIntExtra("quantityForIntent", 0);
        double price = receivedOrderIntent.getDoubleExtra("priceForIntent", 0);
        double orderPrice = receivedOrderIntent.getDoubleExtra("orderPriceForIntent", 0);
        TextView customerNameTextView = findViewById(R.id.userNameTextView);
        TextView goodsNameTextView = findViewById(R.id.goodsNameTextView);
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        TextView priceTextView = findViewById(R.id.priceTextView);
        TextView orderPriceTextView = findViewById(R.id.orderPriceTextView);
        customerNameTextView.setText(userName);
        goodsNameTextView.setText(goodsName);
        quantityTextView.setText(""+quantity);
        priceTextView.setText(""+price);
        orderPriceTextView.setText(""+orderPrice);
        emailText = "Customer name: "+userName+"\n"+
                "Goods name: "+goodsName+"\n"+
                "Quantity: "+quantity+"\n"+
                "Price: "+price+"\n"+
                "Order Price : "+orderPrice+"\n";
    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}