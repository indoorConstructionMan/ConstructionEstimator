package com.example.dubsy.constructionestimator;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.dubsy.constructionestimator.Adapters.MyPagesAdapter;

public class DetailedJobActivity extends AppCompatActivity {

    String pageData[];          //Stores the text to swipe.
    LayoutInflater inflater;    //Used to create individual pages
    ViewPager vp;               //Reference to class to swipe views

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_job);
        pageData=getResources().getStringArray(R.array.detailed_job_tabs);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vp=findViewById(R.id.viewPager);
        vp.setAdapter(new MyPagesAdapter(inflater, pageData));
    }
}
