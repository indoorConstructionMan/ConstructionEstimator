package com.example.dubsy.constructionestimator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dubsy.constructionestimator.Model.ContractsModel;

import java.util.ArrayList;

public class ContractsAdapter extends ArrayAdapter<ContractsModel> {

    public ContractsAdapter(Context context, ArrayList<ContractsModel> contracts) {
        super(context, 0, contracts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_contract_item, parent, false);
        }

        ContractsModel contract = getItem(position);

        TextView address = convertView.findViewById(R.id.address);
        TextView boardFeet = convertView.findViewById(R.id.boardFeet);
        TextView ratePerFoot = convertView.findViewById(R.id.ratePerFoot);

        address.setText(contract.getSiteAddress());
        boardFeet.setText(String.valueOf(contract.getBoardFootage()));
        ratePerFoot.setText(String.valueOf(contract.getRate()));

        return convertView;
    }
}
