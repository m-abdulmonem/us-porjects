package com.us.chat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference reference;
    TextView userame,pass,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userame = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        email = findViewById(R.id.email);

        Button button = findViewById(R.id.button);



        auth = FirebaseAuth.getInstance();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!userame.getText().toString().isEmpty())
                    register(userame.getText().toString(),email.getText().toString(),pass.getText().toString());

            }
        });


    }


    public void register(final String username, String email, String password){

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    String userID = firebaseUser.getUid();

                    reference = FirebaseDatabase.getInstance().getReference("Users").child(userID);

                    HashMap<String,String> params = new HashMap<>();
                    params.put("id",userID);
                    params.put("username",username);
                    params.put("imageURL","default");

                    reference.setValue(params).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }else{
                    Toast.makeText(MainActivity.this, "You Have AN Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
