package com.us.abuabdo.us.Auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.us.abuabdo.us.Model.Users;
import com.us.abuabdo.us.R;
import com.us.abuabdo.us.Vendor.Database.Columns.UsersTable;
import com.us.abuabdo.us.Vendor.Database.DatabaseHandler;
import com.us.abuabdo.us.Vendor.Helper;

public class Login extends AppCompatActivity {

    private EditText user,pass;
    private int attempt = 0;
    private Users userModel;

    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        userModel = new Users(this);
        databaseHandler = new DatabaseHandler(this);
        UsersTable usersTable = new UsersTable(this);

    }

    public void login(View view){
        userModel.setUsername(user.getText().toString());
        userModel.setPassword(pass.getText().toString());
        userModel.login();
    }

    private Helper helper(){
        return new Helper(this);
    }

    public void register(View view) {
        startActivity(new Intent(this,Register.class));
    }

    public void forgetPassword(View view) {
        startActivity(new Intent(this,ForgetPassword.class));
    }
}
