package com.example.dubsy.constructionestimator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dubsy.constructionestimator.Adapters.ContractsAdapter;
import com.example.dubsy.constructionestimator.Database.ConstructionEstimatorDbHelper;
import com.example.dubsy.constructionestimator.Database.Model.ContractsModel;
import com.example.dubsy.constructionestimator.Utilities.UserSession;

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
        String salutations_text = "Welcome " + UserSession.getInstance().getUserName() + "!";
        salutations.setText(salutations_text);

        String n = UserSession.getInstance().getUserName();
        int uid = ConstructionEstimatorDbHelper.getInstance(getApplicationContext()).getUserId(n);
        UserSession.getInstance().setUserId(uid);

        this.contractsList = findViewById(R.id.contractsList);
        ArrayList<ContractsModel> x = ConstructionEstimatorDbHelper.getInstance(getApplicationContext()).grabAllContracts(true, UserSession.getInstance().getUserId());
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