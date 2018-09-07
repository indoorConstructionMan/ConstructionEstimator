package com.example.dubsy.constructionestimator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dubsy.constructionestimator.Model.ContractsModel;

import java.util.ArrayList;

public class UserMainActivity extends AppCompatActivity {

    TextView salutations;
    ListView contractsList;
    ArrayAdapter<ContractsModel> contractsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        salutations = findViewById(R.id.title);
//        String user = getIntent().getExtras().getString("user");
//        salutations.setText("Welcome " + user + "!");

        this.contractsList = findViewById(R.id.contractsList);
        // currently gets all contracts for all users.
        ArrayList<ContractsModel> x = UsersDbHelper.getInstance(getApplicationContext()).grabAllContracts();
        contractsAdapter = new ContractsAdapter(this, new ArrayList<ContractsModel>());
        contractsAdapter.addAll(x);
        this.contractsList.setAdapter(contractsAdapter);
    }

    public void createNewContract(View view) {
        Intent myIntent = new Intent(UserMainActivity.this, ContractInformationActivity.class);
        UserMainActivity.this.startActivity(myIntent);
        finish();
    }
}