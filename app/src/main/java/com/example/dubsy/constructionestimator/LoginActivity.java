package com.example.dubsy.constructionestimator;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void register(View view) {
        Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.startActivity(myIntent);
    }

    public void login(View view) {
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        if (TextUtils.isEmpty(username.getText()) || TextUtils.isEmpty(password.getText())){
            Toast.makeText(getApplicationContext(), "All fields Required",Toast.LENGTH_SHORT).show();
        } else {
            SQLiteDatabase db = UsersDbHelper.getInstance(getApplicationContext()).getReadableDatabase();
            String[] projection = {
                    BaseColumns._ID,
                    DatabaseSchema.Users.COLUMN_NAME_USERNAME,
                    DatabaseSchema.Users.COLUMN_NAME_EMAIL,
                    DatabaseSchema.Users.COLUMN_NAME_PASSWORD
            };

            String selection = DatabaseSchema.Users.COLUMN_NAME_USERNAME + " = ?";
            String[] selectionArgs = { username.getText().toString() };


            Cursor cursor = db.query(
                    DatabaseSchema.Users.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            String hashedPassword = "";

            ArrayList<String> hashedPasswords = new ArrayList<>();
            while(cursor.moveToNext()) {
                hashedPassword = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseSchema.Users.COLUMN_NAME_PASSWORD));
                hashedPasswords.add(hashedPassword);
            }

            if (hashedPasswords.isEmpty()) {
                Toast.makeText(getApplicationContext(), username.getText().toString() + " does not exist.",Toast.LENGTH_SHORT).show();
            } else if (hashedPasswords.size() == 1) {
                if (hashedPassword.equals(new md5().md5(password.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Logging in.",Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(LoginActivity.this, UserMainActivity.class);
                    myIntent.putExtra("user",username.getText().toString());
                    LoginActivity.this.startActivity(myIntent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Try using the correct password instead.",Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "Something went wrong.",Toast.LENGTH_SHORT).show();
            }

            cursor.close();
            db.close();
        }
    }
}
