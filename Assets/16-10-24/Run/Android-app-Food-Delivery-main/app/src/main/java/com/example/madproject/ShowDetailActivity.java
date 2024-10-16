package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.madproject.Helper.ManagementCart;
import com.example.madproject.activity.Domain.FoodDomain;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView addToCartBtn;
    private TextView titleTxt, feeTxt,descriptionTxt,numberOrderTxt;
    ImageView plusBtn, MinusBtn,picFood;
    private FoodDomain object;
    int numberOrder= 1;
    private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        managementCart = new ManagementCart(this);

        initView();
        getBundle();
    }

    private void getBundle(){
        object=(FoodDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);
        titleTxt.setText(object.getTitle());
        feeTxt.setText("$" + object.getFee());
        descriptionTxt.setText(object.getDecsription());
        numberOrderTxt.setText(String.valueOf(numberOrder));



        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder+1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        MinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOrder);
                managementCart.insertFood(object);

            }
        });
    }
    private void initView(){
        addToCartBtn=findViewById(R.id.AddToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt= findViewById(R.id.pricetxt);
        descriptionTxt = findViewById(R.id.description);
        numberOrderTxt = findViewById(R.id.numberOrder);
        plusBtn = findViewById(R.id.plusBtn);
        MinusBtn= findViewById(R.id.minusBtn);
        picFood = findViewById(R.id.picFood);

    }
}