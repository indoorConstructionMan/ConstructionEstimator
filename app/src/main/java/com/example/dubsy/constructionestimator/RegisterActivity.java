package com.example.dubsy.constructionestimator;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

        SQLiteDatabase db = UsersDbHelper.getInstance(getApplicationContext()).getWritableDatabase();
        ContentValues values = new ContentValues();

        String plaintext = password.getText().toString();
        String plaintextconfirm = password_confirm.getText().toString();

        if(plaintext.matches("") || plaintextconfirm.matches("") || username.getText().toString().matches("") || email.getText().toString().matches("")) {
            Toast.makeText(getApplicationContext(),"You can't leave empty fields.",Toast.LENGTH_SHORT).show();
            empty = true;
        }

        if (new String(plaintext).equals(plaintextconfirm) && !empty) {
            values.put(DatabaseSchema.Users.COLUMN_NAME_USERNAME, username.getText().toString());
            values.put(DatabaseSchema.Users.COLUMN_NAME_EMAIL, email.getText().toString());
            values.put(DatabaseSchema.Users.COLUMN_NAME_PASSWORD, new md5().md5(plaintext));

            long newRowId = db.insert(DatabaseSchema.Users.TABLE_NAME, null, values);
            Toast.makeText(getApplicationContext(),Long.toString(newRowId),Toast.LENGTH_SHORT).show();

            Intent myIntent = new Intent(RegisterActivity.this, UserMainActivity.class);
            myIntent.putExtra("user",username.getText().toString());
            RegisterActivity.this.startActivity(myIntent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(),"Could not create user.",Toast.LENGTH_SHORT).show();
        }

        db.close();

    }

    public void cancel(View view) {
        Toast.makeText(getApplicationContext(),"Account was not created",Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        RegisterActivity.this.startActivity(myIntent);
    }

}
