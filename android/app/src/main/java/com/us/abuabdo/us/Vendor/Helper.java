package com.us.abuabdo.us.Vendor;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

public class Helper {

    Context mContext;
    public Helper (Context context){
        this.mContext = context;
    }
    public boolean isEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isInt(String user) {
        return TextUtils.isDigitsOnly(user);
    }

    public void toast(int value){
        Toast.makeText(mContext, value, Toast.LENGTH_SHORT).show();
    }

    public void toast(String value){
        Toast.makeText(mContext, value, Toast.LENGTH_SHORT).show();
    }
}
