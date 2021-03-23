package com.example.supermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.supermarket.Config.DBHelper;
import com.example.supermarket.Model.SalesData;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10, tv11, tv12, tv13, tv14;
    DBHelper dbHelper;
    String userid,Username,usertype,branchtype,compid;
    TextView username;
    List<Map<String,String>> Sales_data = null;
    TextView saledata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbHelper=new DBHelper(this);
        Cursor res = dbHelper.getAllData();

        while (res.moveToNext()) {
//            id = res.getString(0);
            userid = res.getString(1);
            Username = res.getString(2);
            usertype = res.getString(3);
            branchtype = res.getString(4);
            compid = res.getString(5);
        }


        SalesData mydata =new SalesData();
        Sales_data= mydata.doInBackground(branchtype,0);

        Log.e("eeeeeeeeeeee",""+Sales_data.size());

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        username = (TextView) findViewById(R.id.username);
        saledata = (TextView) findViewById(R.id.saledata);



        saledata.setText("Today Sales :"+Sales_data.size());
        username.setText(""+Username);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv1.setBackgroundResource(R.drawable.tab_rounded_filter_selected);
                tv1.setTextColor(Color.parseColor("#FFFFFF"));

                tv2.setBackgroundResource(R.color.white);
                tv2.setTextColor(Color.parseColor("#999999"));

                tv3.setBackgroundResource(R.color.white);
                tv3.setTextColor(Color.parseColor("#999999"));

                tv4.setBackgroundResource(R.color.white);
                tv4.setTextColor(Color.parseColor("#999999"));
                SalesData mydata =new SalesData();
                Sales_data= mydata.doInBackground(branchtype,0);

                Log.e("eeeeeeeeeeee",""+Sales_data.size());
                saledata.setText("Today Sales :"+Sales_data.size());
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv2.setBackgroundResource(R.drawable.tab_rounded_filter_selected);
                tv2.setTextColor(Color.parseColor("#FFFFFF"));

                tv1.setBackgroundResource(R.color.white);
                tv1.setTextColor(Color.parseColor("#999999"));

                tv3.setBackgroundResource(R.color.white);
                tv3.setTextColor(Color.parseColor("#999999"));

                tv4.setBackgroundResource(R.color.white);
                tv4.setTextColor(Color.parseColor("#999999"));
                SalesData mydata =new SalesData();
                Sales_data= mydata.doInBackground(branchtype,1);

                Log.e("eeeeeeeeeeee",""+Sales_data.size());
                saledata.setText("This Week :"+Sales_data.size());
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv3.setBackgroundResource(R.drawable.tab_rounded_filter_selected);
                tv3.setTextColor(Color.parseColor("#FFFFFF"));

                tv1.setBackgroundResource(R.color.white);
                tv1.setTextColor(Color.parseColor("#999999"));

                tv2.setBackgroundResource(R.color.white);
                tv2.setTextColor(Color.parseColor("#999999"));

                tv4.setBackgroundResource(R.color.white);
                tv4.setTextColor(Color.parseColor("#999999"));
                SalesData mydata =new SalesData();
                Sales_data= mydata.doInBackground(branchtype,2);

                Log.e("eeeeeeeeeeee",""+Sales_data.size());

                saledata.setText("This Month :"+Sales_data.size());
            }
        });


        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv4.setBackgroundResource(R.drawable.tab_rounded_filter_selected);
                tv4.setTextColor(Color.parseColor("#FFFFFF"));

                tv1.setBackgroundResource(R.color.white);
                tv1.setTextColor(Color.parseColor("#999999"));

                tv2.setBackgroundResource(R.color.white);
                tv2.setTextColor(Color.parseColor("#999999"));

                tv3.setBackgroundResource(R.color.white);
                tv3.setTextColor(Color.parseColor("#999999"));

                SalesData mydata =new SalesData();
                Sales_data= mydata.doInBackground(branchtype,3);

                Log.e("eeeeeeeeeeee",""+Sales_data.size());

                saledata.setText("This Year :"+Sales_data.size());
            }
        });
    }
}