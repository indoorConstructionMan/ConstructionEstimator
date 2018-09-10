package com.example.dubsy.constructionestimator.Adapters;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dubsy.constructionestimator.R;

public class MyPagesAdapter extends PagerAdapter {

    private String[] pageData;
    private LayoutInflater li;

    public MyPagesAdapter(LayoutInflater li, String[] pd){
        this.pageData = pd;
        this.li = li;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View page;

        switch(position) {
            case 0:
                // Analytics
                page = li.inflate(R.layout.partial_analytics, container, false);
                ((TextView)page.findViewById(R.id.textMessage)).setText(this.pageData[position]);
            case 1:
                // Timer
                page = li.inflate(R.layout.partial_timer, container, false);
                ((TextView)page.findViewById(R.id.textMessage1)).setText(this.pageData[position]);

            case 2:
                // Board
                page = li.inflate(R.layout.partial_board, container, false);
                ((TextView)page.findViewById(R.id.textMessage2)).setText(this.pageData[position]);

            case 3:
                // Employee
                page = li.inflate(R.layout.partial_employee, container, false);
                ((TextView)page.findViewById(R.id.textMessage3)).setText(this.pageData[position]);

            default:
                page = li.inflate(R.layout.partial_analytics, container, false);
                ((TextView)page.findViewById(R.id.textMessage)).setText(this.pageData[position]);
        }

        container.addView(page, 0);
        return page;
    }
    @Override
    public boolean isViewFromObject(@NonNull View arg0, @NonNull Object arg1) {
        return arg0==arg1;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return this.pageData.length;
    }

}
