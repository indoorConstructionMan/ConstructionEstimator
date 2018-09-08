package com.example.dubsy.constructionestimator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dubsy.constructionestimator.Database.Model.ContractsModel;
import com.example.dubsy.constructionestimator.Database.UsersDbHelper;

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
        this.contractsAdapter = new ContractsAdapter(this, new ArrayList<ContractsModel>());
        this.contractsAdapter.addAll(x);
        this.contractsList.setAdapter(contractsAdapter);

        this.contractsList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), String.valueOf(i),Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(view.getContext(), DetailedJobActivity.class);
                startActivityForResult(myIntent, 0);

            }
        });
    }

    public void createNewContract(View view) {
        Intent myIntent = new Intent(UserMainActivity.this, ContractInformationActivity.class);
        UserMainActivity.this.startActivity(myIntent);
        finish();
    }
}