package com.example.dubsy.constructionestimator;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dubsy.constructionestimator.Model.UsersModel;

public class RegisterActivity extends AppCompatActivity {


    EditText username, email, password, password_confirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    // Still needs to confirm that no users exist with username. and i know md5 for passwords......
    public void save(View view) {
        boolean empty = false;

        username = findViewById(R.id.username);
        email = findViewById(R.id.email_address);
        password = findViewById(R.id.password);
        password_confirm = findViewById(R.id.confirm_password);

        String plaintext = password.getText().toString();
        String plaintextconfirm = password_confirm.getText().toString();

        if(plaintext.matches("") || plaintextconfirm.matches("") || username.getText().toString().matches("") || email.getText().toString().matches("")) {
            Toast.makeText(getApplicationContext(),"You can't leave empty fields.",Toast.LENGTH_SHORT).show();
            empty = true;
        }

        UsersModel user = new UsersModel(username.getText().toString(), email.getText().toString(), new md5().md5(plaintext));
        if (UsersDbHelper.getInstance(getApplicationContext()).userExist(user)) {
            Toast.makeText(getApplicationContext(),"Username already exists.",Toast.LENGTH_SHORT).show();
            empty = true;
        }

        if (new String(plaintext).equals(plaintextconfirm) && !empty) {

            UsersDbHelper.getInstance(getApplicationContext()).createUserAccount(user);

            Intent myIntent = new Intent(RegisterActivity.this, UserMainActivity.class);
            myIntent.putExtra("user",username.getText().toString());
            RegisterActivity.this.startActivity(myIntent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(),"Could not create user.",Toast.LENGTH_SHORT).show();
        }

    }

    public void cancel(View view) {
        Toast.makeText(getApplicationContext(),"Account was not created",Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        RegisterActivity.this.startActivity(myIntent);
    }

}
