package com.us.abuabdo.us.Model;

import android.content.Context;
import android.database.Cursor;

import com.us.abuabdo.us.R;
import com.us.abuabdo.us.Vendor.Auth;
import com.us.abuabdo.us.Vendor.Database.Columns.UsersTable;
import com.us.abuabdo.us.Vendor.Database.Handler;

public class Users extends Handler {
    private int id;
    private String username;
    private String password;
    private String email;
    private String name;
    private int phone;

    private int ATTEMPT = 5;
    private int ATTEMPT_COUNT = 0;

    private Context context;


    public Users(Context context) {
        super(context);
        this.context = context;
    }


    public void login(){

        if (ATTEMPT_COUNT == ATTEMPT) {
            helper().toast(R.string.attempt_msg);
        } else {

            if (getUsername().equals("")) {
                helper().toast(R.string.empty_password);
                setAttemptCount();
            }
            if (getUsername().equals("")) {
                helper().toast(R.string.empty_username);
                setAttemptCount();
            }
            if (getUsername().equals("") && getPassword().equals("")) {
                helper().toast( R.string.empty_login_fields);
                setAttemptCount();
            }

            Cursor user = usersTable().getLoginUser(getUsername());

            while (user.moveToNext()) {

                int id = user.getInt(0);
                String username = user.getString(1);
                String password = user.getString(2);
                if (username.equals(getUsername()) && password.equals(getPassword())) {
//                    auth().setLogin(id, username, password);
                    auth().setLoginStatus(true);
                    helper().toast(R.string.user_logging);
                } else {
                    helper().toast("Logging error");
                    if (!username.equals(getUsername())) {
                        helper().toast(R.string.username_not_vaild);
                        setAttemptCount();
                    }
                    if (!password.equals(getPassword())) {
                        helper().toast(R.string.password_not_vaild);
                        setAttemptCount();
                    }
                }
            }
        }
    }





    private UsersTable usersTable(){ return new UsersTable(context); }
    private Auth auth(){
        return  new Auth(context);
    }

    public int setAttemptCount(){
        return this.ATTEMPT_COUNT += 1;
    }

    // Getter
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public int getPhone() { return phone; }
    public String getName() {
        return name;
    }

    // Setter
    public void setId(int id) {
        this.id = id;
    }
    public String setUsername(String username) {
        return this.username = username;
    }
    public String setPassword(String password) {
        return this.password = password;
    }
    public String setName(String name) {
        return this.name = name;
    }
    public int setPhone(int phone) {
        return this.phone = phone;
    }


}
