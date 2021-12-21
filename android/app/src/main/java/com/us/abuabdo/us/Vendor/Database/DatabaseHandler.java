package com.us.abuabdo.us.Vendor.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.us.abuabdo.us.Vendor.Database.Columns.UsersTable;
import com.us.abuabdo.us.Vendor.Helper;

import static android.content.ContentValues.TAG;

/**
 * Created by mohamed on 05/01/18.
 */

public class DatabaseHandler extends SQLiteOpenHelper implements DatabaseConfig {


   final private static  String DATABASE_NAME = "us";
   final private static  int DATABASE_VERSION = 1;

   final private static  String ID = "id";
   final private static  String TIME = "time";
   final private static  String HOUR = "hour";
   final private static  String MINUTE = "minute";
   final private static  String SECOND = "second";
   final private static  String NAME = "name";
   final private static  String NOTE = "note";
   final private static  String READ = "read";


   final private static  String DB_NAME_TODO_LIST = "todoList";
   final private static  String TODO_ID = "id";
   final private static  String TODO_NOTE = "note";
   final private static  String TODO_LABEL = "label";
   final private static  String TODO_ACHIEVED = "achieved";



   private Exception error;
   private Context context;
    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL(createUsers());
        } catch (SQLException SQLError){
            Toast.makeText(context, "Error Founded : " + error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try{
            sqLiteDatabase.execSQL(dropUsers());
            onCreate(sqLiteDatabase);
        } catch (SQLException SQLError){
            Toast.makeText(context, "Error Founded : " + error, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *
     * @return SQLiteDatabase
     */
    public SQLiteDatabase sqLiteDatabase(){
        return this.getWritableDatabase();
    }

    /**
     *
     * @return
     */
    public ContentValues contentValues(){
        return  new ContentValues();
    }


    public boolean addUser(String username,String email,String password){
        contentValues().put(UsersTable.USERNAME,username);
        contentValues().put(UsersTable.EMAIL,email);
        contentValues().put(UsersTable.PASSWORD,password);

        long result = sqLiteDatabase().insert(UsersTable.TABLE_NAME,null,contentValues());
        return result != -1;
    }

    public Cursor updateUser(){
//        String Query = "UPDATE " + DATABASE_NAME + " SET " + TIME + " = '" + Time + "',"
//                + HOUR + " = '" + hour + "', " + MINUTE + " = '" + minute + "', " +
//                NAME + " = '"+name+"', " + NOTE + " = '" + Note + "'," +
//                READ + " = '" + read + "'  WHERE " + ID + " = '" + id + "'";
//        sqLiteDatabase().execSQL(Query);

        return null;
    }

    public Cursor getLoginUser(String user){

        String userAuth;
        if (helper().isEmail(user))
            userAuth = UsersTable.EMAIL;
        else if (helper().isInt(user))
            userAuth = UsersTable.PHONE;
        else
            userAuth = UsersTable.USERNAME;

        String query = "SELECT * FROM " + UsersTable.TABLE_NAME +
                " WHERE " + userAuth + " = '" + user + "'";

        return sqLiteDatabase().rawQuery(query,null);

    }

    public Cursor getData(String tableName){
        String query = "SELECT * FROM " + tableName;
        return sqLiteDatabase().rawQuery(query,null);
    }

    public Cursor getDataID(String Time){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + ID + " FROM " + DATABASE_NAME +
                " WHERE " + TIME + " = '" + Time + "'";
        return db.rawQuery(query,null);
    }

    /**
     *
     * @param id
     * @return
     */
    public Cursor getDataByID(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + DATABASE_NAME +
                " WHERE " + ID + " = '" + id + "'";
        return db.rawQuery(query,null);
    }

    /**
     *
     * @param id
     * @param Time
     * @param hour
     * @param minute
     * @param name
     * @param Note
     * @param read
     */
    public void UpdateItemData(int id , String Time, int hour, int minute, String name, String Note, int read){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String Query = "UPDATE " + DATABASE_NAME + " SET " + TIME + " = '" + Time + "',"
                + HOUR + " = '" + hour + "', " + MINUTE + " = '" + minute + "', " +
                NAME + " = '"+name+"', " + NOTE + " = '" + Note + "'," +
                READ + " = '" + read + "'  WHERE " + ID + " = '" + id + "'";
        sqLiteDatabase.execSQL(Query);
    }

    /**
     *
     * @param id
     */
    public void deleteItem(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String Query = "DELETE FROM " + DATABASE_NAME + " WHERE " + ID + " = '" + id + "'";
        sqLiteDatabase.execSQL(Query);
    }


    /**
     *
     * DATABASE_NAME List Queries
     *
     *
     */


    /******
     *
     *
     * @param  id
     */
    public void deleteTodo(int id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String Query = "DELETE FROM " + DB_NAME_TODO_LIST + " WHERE " + TODO_ID + " = '" + id + "'";

        sqLiteDatabase.execSQL(Query);
    }

    /**
     * GetDATABASE_NAMEByID
     * @param id
     * @return Cursor
     */
    public Cursor getTodoByID(int id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String query = "SELECT * FROM " + DB_NAME_TODO_LIST +
                " WHERE " + TODO_ID + " = '" + id + "'";

        return sqLiteDatabase.rawQuery(query,null);
    }

    /**
     * Get All Data
     * @return Cursor
     */
    public Cursor getAllTodo() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String query = "SELECT * FROM " + DB_NAME_TODO_LIST;

        return sqLiteDatabase.rawQuery(query,null);
    }

    /**
     * Get getTodo id
     * @param todoLabel
     * @return
     */
    public Cursor getID(String todoLabel){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String query = "SELECT " + TODO_ID + " FROM " + DB_NAME_TODO_LIST +
                " WHERE " + TODO_LABEL + " = '" + todoLabel + "'";

        return sqLiteDatabase.rawQuery(query,null);
    }

    /**
     * Insert New DATABASE_NAME
     * @param title
     * @param note
     * @param achieved
     * @return void
     */
    public boolean insertTodo(String title, String note, int achieved){

       try {
           SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

           ContentValues contentValues = new ContentValues();

           contentValues.put(TODO_LABEL,title);

           contentValues.put(TODO_NOTE,note);

           contentValues.put(TODO_ACHIEVED,achieved);


           long result = sqLiteDatabase.insert(DB_NAME_TODO_LIST,null,contentValues);


           Log.e(TAG, "add: ");

           if (result == -1)

               return  false;

           else

               return true;
       } catch (android.database.SQLException e){
           error = e;
       }
        return false;
    }

    public boolean test(){

      try {
          String q = "SELECT * FROM " + DB_NAME_TODO_LIST;
          SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
          sqLiteDatabase.execSQL(q);
          return true;
      }catch (android.database.SQLException e){
          error = e;
      }

        return false;
    }



    private UsersTable users(){
        return new UsersTable(context);
    }

    private String createUsers(){
        users().create();
        return null;
    }
    private String dropUsers(){
        users().drop();
        return null;
    }


    private Helper helper(){
        return new Helper(context);
    }


}
