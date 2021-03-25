package com.example.supermarket.Model;

import android.util.Log;

import com.example.supermarket.Connection.ConnectionStr;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchasereturnData {
    Connection connect;
    String ConnectionResult = "";
    Boolean isSuccess = false;

    public List<Map<String,String>> doInBackground(String branchid, int filter) {

        List<Map<String, String>> data = null;
        data = new ArrayList<Map<String, String>>();
        try
        {
            ConnectionStr conStr=new ConnectionStr();
            connect =conStr.connectionclasss();        // Connect to database
            if (connect == null)
            {
                ConnectionResult = "Check Your Internet Access!";
            }
            else
            {
                // Change below query according to your own database.
                String yearid = null;
                String query=null;



                if (filter == 0){
                    //day
                    query = "select * from V_Purchase_Return_HDR where Branch_id="+branchid+" and Purchase_Return_Date=cast(getdate() as Date)";
                }else if (filter == 1){
                    //week
                    query = "select * from V_Purchase_Return_HDR where Branch_id="+branchid+" and Purchase_Return_Date BETWEEN DATEADD(DAY, -7, GETDATE()) AND DATEADD(DAY, 1, GETDATE())";
                }else if (filter == 2){
                    //month
                    query = "select * from V_Purchase_Return_HDR where Branch_id="+branchid+" and datepart(mm,Purchase_Return_Date) =month(getdate()) and datepart(yyyy,Purchase_Return_Date) =year(getdate())";
                }else if (filter == 3){
                    //year
                    String year = "select * from M_Financial_Year where Year_Id = (select max(Year_Id) from M_Financial_Year)";
                    Statement stmtq = connect.createStatement();
                    ResultSet rs1 = stmtq.executeQuery(year);

                    while (rs1.next()){
                        yearid=rs1.getString("Year_Id");
                    }
                    Log.e("yearid",""+yearid);
                    query = "select * from V_Purchase_Return_HDR where Branch_id="+branchid+" and Year_id="+yearid;
                }






                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                Log.e("query",""+query);
                while (rs.next()){
                    Map<String,String> datanum=new HashMap<String,String>();
                    datanum.put("Purchase_Return_Date",rs.getString("Purchase_Return_Date"));
                    datanum.put("Total_amount",rs.getString("Total_amount"));
                    datanum.put("Purchase_Return_Id",rs.getString("Purchase_Return_Id"));
                    data.add(datanum);
                }


                ConnectionResult = " successful";
                isSuccess=true;
                connect.close();
            }
        }
        catch (Exception ex)
        {
            isSuccess = false;
            ConnectionResult = ex.getMessage();
        }

        return data;
    }
}
