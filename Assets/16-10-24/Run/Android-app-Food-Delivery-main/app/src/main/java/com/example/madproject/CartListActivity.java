package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.madproject.Adaptor.CartListAdaptor;
import com.example.madproject.Helper.ManagementCart;
import com.example.madproject.Interface.ChangeNumberItemsListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;

    TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt, Checkout;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        managementCart=new ManagementCart(this);
        initView();
        initlist();
        calculateCart();
        bottomNavigation();
    }
    private  void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homebtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(CartListActivity.this, CartListActivity.class)});
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(CartListActivity.this, MenuAvtivity.class)});
            }
        });
    }
    private void initView() {
        recyclerViewList=findViewById(R.id.recyclerView);
        totalFeeTxt=findViewById(R.id.deliveryTxt);
        taxTxt=findViewById(R.id.deliveryTxt);
        deliveryTxt=findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);
        emptyTxt = findViewById(R.id.EmptyTxt);
        scrollView=findViewById(R.id.scrollView3);
        recyclerViewList=findViewById(R.id.cartView);

    }
    private void initlist(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdaptor(managementCart.getListCart(),this, new ChangeNumberItemsListener(){
            @Override
            public void changed() {
                calculateCart();
            }
        });
        recyclerViewList.setAdapter(adapter);
        if(managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }
        else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    private void calculateCart(){
        double percentTax = 0.02;
        double delivery = 10;

        tax = Math.round((managementCart.getTotalFee()* percentTax)*100) / 100;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100) / 100;

        totalFeeTxt.setText("$" + itemTotal);
        taxTxt.setText("$" + tax);
        deliveryTxt.setText("$" + delivery);
        totalTxt.setText("$" + total);

    }
}