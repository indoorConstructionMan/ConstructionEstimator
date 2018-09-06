package com.example.dubsy.constructionestimator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserMainActivity extends AppCompatActivity {

    TextView salutations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        salutations = findViewById(R.id.title);
        String user = getIntent().getExtras().getString("user");
        salutations.setText("Welcome " + user + "!");
    }


    public void createNewContract(View view) {
        Intent myIntent = new Intent(UserMainActivity.this, ContractInformationActivity.class);
        UserMainActivity.this.startActivity(myIntent);
    }
}