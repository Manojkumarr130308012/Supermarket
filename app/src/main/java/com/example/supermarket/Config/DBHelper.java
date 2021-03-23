package com.example.supermarket.Config;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    //    Cursor cursor;
    SQLiteDatabase database;

    public static final String DATABASE_NAME="MyDb.db";
    public static final String TABLE_NAME="user";

    public static final String COL_1="ID";
    public static final String COL_2="User_id";
    public static final String COL_3="User_Name";
    public static final String COL_4="User_Type";
    public static final String COL_5="Branch_Id";
    public static final String COL_6="Comp_Id";




    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER,ip TEXT,db TEXT,user TEXT,pass TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID TEXT,User_id TEXT,User_Name TEXT,User_Type TEXT,Branch_Id TEXT,Comp_Id TEXT)");


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME); //Drop older table if exists

        onCreate(db);
    }

    public boolean insertData(String User_id, String User_Name, String User_Type, String Branch_Id, String Comp_Id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put("ID", ModelClass.id);
        contentValues.put("User_id", User_id);
        contentValues.put("User_Name", User_Name);
        contentValues.put("User_Type", User_Type);
        contentValues.put("Branch_Id", Branch_Id);
        contentValues.put("Comp_Id", Comp_Id);

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return true;
    }



    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    public Boolean checkForm(String name, String pass, String org){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select * from "+TABLE_NAME +" where "+COL_2+" = ? and "+COL_3+" = ?",new String[] { name,pass,org });
        if (res.getCount()>0) return true;
        else return false;

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


    public Cursor liteDatabaseRecords(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);

        return cursor;

    }

   


    public void deleteRow()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME);
        db.close();

    }

/*
    public boolean updateData (String name, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put("id", GetData.id);
        contentValues.put("name", name);
        contentValues.put("pass", pass);
        db.update(TABLE_NAME, contentValues, "ID = ? ", new String[] { Integer.toString(GetData.id) } );
        return true;
    }
*/










}
