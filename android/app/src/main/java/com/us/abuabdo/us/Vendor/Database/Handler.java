package com.us.abuabdo.us.Vendor.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.us.abuabdo.us.Vendor.Database.Columns.UsersTable;
import com.us.abuabdo.us.Vendor.Helper;

public class Handler extends SQLiteOpenHelper implements DatabaseConfig{


    private Context mContext;

    public Handler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            sqLiteDatabase.execSQL(createUsers());
        }catch(SQLException sqlError){

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try{
            sqLiteDatabase.execSQL(dropUsers());

            onCreate(sqLiteDatabase);
        } catch (SQLException SQLError){
            Toast.makeText(mContext, "Error Founded : " + SQLError, Toast.LENGTH_SHORT).show();
        }
    }
    public SQLiteDatabase sqLiteDatabase(){
        return this.getWritableDatabase();
    }

    public ContentValues contentValues(){
        return new ContentValues();
    }

    public Helper helper(){
        return new Helper(mContext);
    }

    public Cursor getDataByID(int id,String tableName,String selector){
        String query = "SELECT * FROM " + tableName + " WHERE " + selector + " = '" +  id +"'"  ;
        return null;
    }
//    public Cursor getDataBy(String key,String value,String tableName){
//        String query = "SELECT * FROM " + tableName + " WHERE " + selector + " = '" +  id +"'"  ;
//    }
//    public Cursor getDataBy(String key,int value,String tableName){
//
//    }
    private String createUsers(){
        return  UsersTable.create();
    }
    private String dropUsers(){
        return UsersTable.drop();
    }
}
