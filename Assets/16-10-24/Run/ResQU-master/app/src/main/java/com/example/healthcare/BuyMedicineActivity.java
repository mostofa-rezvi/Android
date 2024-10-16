package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {

    String[][] packages={
            { " Paracetamol (Acetaminophen)"," "," "," ","50"},
            {"Ibuprofen"," "," "," ","305"},
            { "Amoxicillin"," "," "," ","200"},
            {"Lisinopril"," "," "," ","107"},
            {"Atorvastatin"," "," "," ","40"},
            {"Metformin"," "," "," ","52"},
            {" Omeprazole"," "," "," ","25"},
            {" Levothyroxine"," "," "," ","30"},
            {" Amlodipine"," "," "," ","108"}
    };
    String[] package_details={"Pain relief and fever reduction\n"+
            "Nonsteroidal anti-inflammatory drug (NSAID) for pain, inflammation, and fever\n"+
            "Antibiotic used to treat bacterial infections such as sinusitis, pneumonia, and ear infections\n"+
            " ACE inhibitor used to treat high blood pressure (hypertension) and heart failure\n"+
            "Lowers cholesterol and reduces the risk of heart disease\n"+
            "Used to treat type 2 diabetes by controlling blood sugar levels\n"+
            "Proton pump inhibitor used for acid reflux, GERD, and stomach ulcers\n"+
            " Hormone replacement for hypothyroidism (low thyroid hormone levels)\n"+
            "Calcium channel blocker used to treat high blood pressure and angina (chest pain)"

    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGoToCart,btnBack;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);
        listView=findViewById(R.id.listViewBM);
        btnGoToCart=findViewById(R.id.buttonBMDAddToCart);
        btnBack=findViewById(R.id.buttonBMGoBack);

      btnBack.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
          }
      });
      btnGoToCart.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
         startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
          }
      });
        list=new ArrayList();
        for(int i=0;i<packages.length;i++){
            item =new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost:"+packages[i][4]+"/-");
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,
                R.layout.mulit_lines,
                new String []{"line1","line2","line3","line4","line5"},
                new int []{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        //ListView lst=findViewById(R.id.listViewLT);
        listView.setAdapter(sa);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it=new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });

    }
}