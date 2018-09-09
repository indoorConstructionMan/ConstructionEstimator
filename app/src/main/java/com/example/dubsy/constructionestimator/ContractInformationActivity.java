package com.example.dubsy.constructionestimator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dubsy.constructionestimator.Database.ConstructionEstimatorDbHelper;
import com.example.dubsy.constructionestimator.Database.Model.ContractsModel;


public class ContractInformationActivity extends AppCompatActivity {

    public EditText siteAddress, siteBoardFeet, siteRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_information);
        this.siteAddress = findViewById(R.id.siteAddress);
        this.siteBoardFeet = findViewById(R.id.siteBoardFeet);
        this.siteRate = findViewById(R.id.siteRate);
    }

    public void saveContract(View v) {
        Toast.makeText(getApplicationContext(), "Contract Saved!",Toast.LENGTH_SHORT).show();
        ContractsModel m = new ContractsModel(
                this.siteAddress.getText().toString(),
                this.siteBoardFeet.getText().toString(),
                this.siteRate.getText().toString()
        );

        ConstructionEstimatorDbHelper.getInstance(getApplicationContext()).createContract(m);
        Toast.makeText(getApplicationContext(), "Contract saved!",Toast.LENGTH_SHORT).show();
    }

    public void backToUserActivity(View v) {
        Intent myIntent = new Intent(ContractInformationActivity.this, com.example.dubsy.constructionestimator.UserMainActivity.class);
        ContractInformationActivity.this.startActivity(myIntent);
        finish();
    }
}
