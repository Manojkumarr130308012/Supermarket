package com.example.supermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.supermarket.Config.DBHelper;
import com.example.supermarket.Connection.ConnectionStr;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends AppCompatActivity {
    ProgressBar progressBar;
    Connection connect;
    EditText username,password;
    TextView logbtn;
    String UserNameStr,PasswordStr;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressBar = (ProgressBar) findViewById(R.id.progress_circular);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        logbtn=findViewById(R.id.logbtn);
        dbHelper=new DBHelper(this);

        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserNameStr=username.getText().toString();
                PasswordStr=password.getText().toString();

                checklogin check_Login = new checklogin();
                check_Login.execute(UserNameStr,PasswordStr);
            }
        });
    }


    public class checklogin extends AsyncTask<String,String,String>
    {
        String ConnectionResult = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute()
        {

            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected void onPostExecute(String result)
        {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(Login.this, result, Toast.LENGTH_SHORT).show();
            if(isSuccess)
            {
                Toast.makeText(Login.this , "Login Successfull" , Toast.LENGTH_LONG).show();
                //finish();
            }
        }
        @Override
        protected String doInBackground(String... params) {
            String usernam = UserNameStr;
            String passwordd =PasswordStr;
            if(usernam.trim().equals("")|| passwordd.trim().equals(""))
                ConnectionResult = "Please enter Username and Password";
            else
            {
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
                        String query = "select * from M_User_Name where User_Name= '" + usernam + "' and User_Password = '"+ passwordd +"'  ";
                        Statement stmt = connect.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        if(rs.next())
                        {
                            Log.e("dddddddddddddd",""+rs.getString("id"));
                            dbHelper.insertData(rs.getString("id"),rs.getString("User_Name"),rs.getString("User_Type"),rs.getString("Branch_Id"),rs.getString("Comp_Id"));
                            Intent i=new Intent(Login.this,MainActivity.class);
                            startActivity(i);
                            ConnectionResult = "Login successful";
                            isSuccess=true;
                            connect.close();
                        }
                        else
                        {
                            ConnectionResult = "Invalid Credentials!";
                            isSuccess = false;
                        }
                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    ConnectionResult = ex.getMessage();
                }
            }
            return ConnectionResult;
        }
    }
}