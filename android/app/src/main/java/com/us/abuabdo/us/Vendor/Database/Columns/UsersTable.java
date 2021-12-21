package com.us.abuabdo.us.Vendor.Database.Columns;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.us.abuabdo.us.R;
import com.us.abuabdo.us.Vendor.Database.Handler;


public class UsersTable extends Handler{


    final public static String TABLE_NAME = "users";
    private final static String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    // Table Columns
    final public static String ID = "id";
    final public static String USERNAME = "username";
    final public static String PASSWORD = "password";
    final public static String EMAIL = "email";
    final public static String PHONE = "phone";

    public UsersTable(@Nullable Context context) {
        super(context);
    }

    public static String create() {
        String create =  "CREATE TABLE " + TABLE_NAME + "( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERNAME + " VARCHAR(225), " + PASSWORD + " VARCHAR(225), " + EMAIL +" VARCHAR(225), " + PHONE +" VARCHAR(225));";
        return create;
    }

    public static String drop() {
        return DROP_TABLE;
    }


    public boolean addUser(String username,String email,String password,int phone){
        contentValues().put(UsersTable.USERNAME,username);
        contentValues().put(UsersTable.EMAIL,email);
        contentValues().put(UsersTable.PASSWORD,password);
        contentValues().put(UsersTable.PHONE,phone);

        long result = sqLiteDatabase().insert(UsersTable.TABLE_NAME,null,contentValues());
        return result != -1;
    }

    public void updateUser(int id,String username,String password,String email,int phone){
        String Query = "UPDATE " + TABLE_NAME + " SET " + USERNAME + " = '" + username + "',"
                + PASSWORD + " = '" + password + "', " + EMAIL + " = '" + email + "', " +
                PHONE + " = '" + phone + "' WHERE " + ID + " = '" + id + "'";

        sqLiteDatabase().execSQL(Query);
    }

    public void updateUserPassword(String password){
        if (password.equals("")){

        }
    }
    public Cursor getLoginUser(String user){

        String userAuth = UsersTable.USERNAME;
        String query;
        if (user.isEmpty())
            helper().toast(R.string.empty_username);
        else if (helper().isInt(user))
            userAuth = UsersTable.PHONE;
        else if (helper().isEmail(user))
            userAuth = UsersTable.EMAIL;
        else
            userAuth = UsersTable.USERNAME;

        query = "SELECT * FROM " + UsersTable.TABLE_NAME +
                " WHERE " + userAuth + " = '" + user + "'";

        return sqLiteDatabase().rawQuery(query, null);
    }

}
