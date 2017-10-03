package com.salajim.musab.newshub.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.salajim.musab.newshub.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsCategoryActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.latestNewsBtn) Button mLatestNewsBtn;
    @Bind(R.id.generalNewsBtn) Button mGeneralNewsBtn;
    @Bind(R.id.techNewsBtn) Button mTechNews;
    @Bind(R.id.businessNewsBtn) Button mBusinessNewsBtn;
    @Bind(R.id.entertainmentNewsBtn) Button mEnterTainmentNewsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_category);
        ButterKnife.bind(this);

        mLatestNewsBtn.setOnClickListener(this);
        mGeneralNewsBtn.setOnClickListener(this);
        mTechNews.setOnClickListener(this);
        mBusinessNewsBtn.setOnClickListener(this);
        mEnterTainmentNewsBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mLatestNewsBtn) {
            Intent intent = new Intent(NewsCategoryActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if(v == mGeneralNewsBtn) {
            Intent intent = new Intent(NewsCategoryActivity.this, GeneralNewsActivity.class);
            startActivity(intent);
        }
        if(v == mTechNews) {
            Intent intent = new Intent(NewsCategoryActivity.this, TechnologyNewsActivity.class);
            startActivity(intent);
        }
        if(v == mBusinessNewsBtn) {
            Intent intent = new Intent(NewsCategoryActivity.this, BusinessNewsActivity.class);
            startActivity(intent);
        }
        if(v == mEnterTainmentNewsBtn) {
            Intent intent = new Intent(NewsCategoryActivity.this, EntertainmentNewsActivity.class);
            startActivity(intent);
        }
    }
}
