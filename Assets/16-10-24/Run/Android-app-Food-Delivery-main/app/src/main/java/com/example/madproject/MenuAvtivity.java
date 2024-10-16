package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.madproject.Adaptor.CatagoryAdaptor;
import com.example.madproject.Adaptor.PopularAdaptor;
import com.example.madproject.activity.Domain.CatagoryDomain;
import com.example.madproject.activity.Domain.FoodDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MenuAvtivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCatagorylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_avtivity);
        recyclerViewCatagory2();
        recyclerViewCatagory();
        bottomNavigation();
    }

    private  void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homebtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(MenuAvtivity.this, CartListActivity.class)});
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(MenuAvtivity.this, MenuAvtivity.class)});
            }
        });

    }
    private void recyclerViewCatagory2() {

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            recyclerViewCatagorylist = findViewById(R.id.recyclerView2);
            recyclerViewCatagorylist.setLayoutManager(linearLayoutManager);

            ArrayList<CatagoryDomain> category = new ArrayList<>();
            category.add(new CatagoryDomain("Pizza", "cat_1"));
            category.add(new CatagoryDomain("Burger", "cat_2"));
            category.add(new CatagoryDomain("Hot Dog", "cat_3"));
            category.add(new CatagoryDomain("Drinks", "cat_4"));
            category.add(new CatagoryDomain("Donuts", "cat_5"));

            adapter = new CatagoryAdaptor(category);
            recyclerViewCatagorylist.setAdapter(adapter);
    }

    private void recyclerViewCatagory(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCatagorylist = findViewById(R.id.recyclerView);
        recyclerViewCatagorylist.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodList = new ArrayList<>();
        foodList.add(new FoodDomain("Peproni Pizza", "pizza", "Sliced peproni, Cheese, BBQ Chunks, Vegitables", 10.50));
        foodList.add(new FoodDomain("BBQ Pizza", "pop_1", "Grilled Chicken, Cheese, BBQ Chunks, Vegitables", 12.50));
        foodList.add(new FoodDomain("Beef Burger", "pop_2", "Smash Beef Grilled, Hot Sauce, Cheese, Mashrooms , Vegitables", 8.50));
        foodList.add(new FoodDomain("Paproni Pizza", "pop_3", "Paproni , Grilled Chicken, Cheese, BBQ Chunks, Vegitables", 15.50));

        adapter = new PopularAdaptor(foodList);
        recyclerViewCatagorylist.setAdapter(adapter);


    }
}