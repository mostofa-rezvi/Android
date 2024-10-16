package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1= {
            {"Doctor Name :Ajit Saste","Hospital Address:Pimpri,","Exp: 5yrs","Mobile No:9898989898","600"},
            {"Doctor Name :Prasad Pawar","Hospital Address:Nigdi,","Exp: 15yrs","Mobile No:7158964752" ,"900"},
            {"Doctor Name :Swapnil Kale","Hospital Address:Pune,","Exp: 8yrs","Mobile No:9785412358","300"},
            {"Doctor Name :Deepak Deshmukh","Hospital Address:Chinchwad,","Exp: 6yrs","Mobile No:8574693127","500"},
            {"Doctor Name :Ashok Panda","Hospital Address:Katraj,","Exp: 7yrs","Mobile No:9855779898","800"}
    };
    private String[][] doctor_details2= {
            {"Doctor Name:Dr. Rahul Mehta", "Hospital Address:123MG Road, Mumbai, Maharashtra ","Exp:12years","Mobile NO:9876543210", "600"},
            {"Doctor Name:Dr. Priya Sharma", "Hospital Address:456 Park St, Kolkata, West Bengal ","Exp:8years","Mobile NO:9876543211", "300"},
            {"Doctor Name:Dr. Anil Gupta", "Hospital Address:789 Residency Rd, Bangalore, Karnataka ","Exp:15years","Mobile NO:9876543212", "800"},
            {"Doctor Name:Dr. Sunita Verma", "Hospital Address:101 Connaught Place, New Delhi, Delhi","Exp:20years","Mobile NO:9876543213", "700"},
            {"Doctor Name:Dr. Vikram Patel", "Hospital Address:202 Ring Road, Ahmedabad, Gujarat ","Exp:10years","Mobile NO:9876543214", "500"}
    };
    private String[][] doctor_details3= {
            {"Doctor Name:Dr. Kavita Rao", "Hospital Address:303 Banjara Hills, Hyderabad, Telangana","Exp:5years","Mobile NO:9876543215", "100"},
            {"Doctor Name:Dr. Rajesh Singh", "Hospital Address:404 Anna Salai, Chennai, Tamil Nadu ","Exp:18years","Mobile NO:9876543216", "200"},
            {"Doctor Name:Dr. Sneha Iyer", "Hospital Address:505 Sector 17, Chandigarh, Punjab ","Exp:7years","Mobile NO:9876543217", "300"},
            {"Doctor Name:Dr. Amit Chatterjee", "Hospital Address:606 VIP Road, Bhubaneswar, Odisha ","Exp:2years","Mobile NO:9876543218", "400"},
            {"Doctor Name:Dr. Neha Jain", "Hospital Address:707 JLN Marg, Jaipur, Rajasthan","Exp:11years","Mobile NO:9876543219", "500"}
    };
    private String[][] doctor_details4= {
            {"Doctor Name:Dr. Dr. Prakash Desai", "Hospital Address:808 FC Road, Pune, Maharashtra ","Exp:4years","Mobile NO:9876543220", "600"},
            {"Doctor Name:Dr. Suresh Reddy","Hospital Address:909 Jubilee Hills, Hyderabad, Telangana",	"Exp:14 years"	,"Mobile No:9876543221, ", "700"},
            {"Doctor Name:Dr. Meenakshi Nair", "Hospital Address:1010 MG Road, Kochi, Kerala","Exp:3years","Mobile NO:9876543222", "800"},
            {"Doctor Name:Dr. Arjun Thakur", "Hospital Address:1111 MI Road, Jaipur, Rajasthan ","Exp:2years","Mobile NO:9876543223", "900"},
            {"Doctor Name:Dr. Deepa Menon", "Hospital Address:1212 Brigade Road, Bangalore, Karnataka ","Exp:16years","Mobile NO:9876543224", "200"}
    };
    private String[][] doctor_details5= {
            {"Doctor Name:Dr. Manish Kapoo", "Hospital Address:11313 Karol Bagh, New Delhi, Delhi ","Exp:16years","Mobile NO:9876543225", "300"},
            {"Doctor Name: Dr. Ritu Agarwal", "Hospital Address:1414 Sadar Bazar, Lucknow, Uttar Pradesh ","Exp:17years","Mobile NO:9876543226", "500"},
            {"Doctor Name:Dr. Rakhi Mehta", "Hospital Address:1515 CG Road, Ahmedabad, Gujarat ","Exp:18years","Mobile NO:9876543227", "400"},
            {"Doctor Name:Dr. Rahul Joshi", "Hospital Address:1616 MG Road, Pune, Maharashtra ","Exp:8years","Mobile NO:9876543228", "700"},
            {"Doctor Name:Dr. Kunal Mehra", "Hospital Address:707 JLN Marg, Jaipur, Rajasthan ","Exp:22years","Mobile NO:9876543229", "600"}
    };

TextView tv;
Button btn;
String [][] doctor_details={};
ArrayList list;
SimpleAdapter sa;
HashMap<String,String> item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv=findViewById(R.id.textViewDDTitle);
        btn=findViewById(R.id.buttonltGoToCart);

        Intent it =getIntent();
        String title=it.getStringExtra("title");
        tv.setText(title);
        if(title.compareTo("Family Physicians")==0)
          doctor_details=doctor_details1;
        else
        if(title.compareTo("Dietitian")==0)
            doctor_details=doctor_details2;
        else
        if(title.compareTo("Dentist")==0)
            doctor_details=doctor_details3;
        else
        if(title.compareTo("Surgeon")==0)
            doctor_details=doctor_details4;
        else
            doctor_details=doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });
        list=new ArrayList();
        for(int i=0;i< doctor_details.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Cons Fees:"+doctor_details[i][4]+"/-");
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,R.layout.mulit_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lst=findViewById(R.id.listViewBM);
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it=new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}