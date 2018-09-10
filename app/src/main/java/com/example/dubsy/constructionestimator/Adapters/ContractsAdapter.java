package com.example.dubsy.constructionestimator.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dubsy.constructionestimator.Database.Model.ContractsModel;
import com.example.dubsy.constructionestimator.R;

import java.util.ArrayList;

public class ContractsAdapter extends ArrayAdapter<ContractsModel> {

    public ContractsAdapter(Context context, ArrayList<ContractsModel> contracts) {
        super(context, 0, contracts);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_contract_item, parent, false);
        }

        ContractsModel contract = getItem(position);

        TextView address = convertView.findViewById(R.id.address);
        TextView boardFeet = convertView.findViewById(R.id.boardFeet);
        TextView ratePerFoot = convertView.findViewById(R.id.ratePerFoot);

        if (contract != null) {
            address.setText(contract.getSiteAddress());
            boardFeet.setText(String.valueOf(contract.getBoardFootage()));
            ratePerFoot.setText(String.valueOf(contract.getRate()));
        }

        return convertView;
    }
}
