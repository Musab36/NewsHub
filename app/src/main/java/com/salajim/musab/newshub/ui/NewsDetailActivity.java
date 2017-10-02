package com.salajim.musab.newshub.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.salajim.musab.newshub.R;
import com.salajim.musab.newshub.adapters.NewsPagerAdapter;
import com.salajim.musab.newshub.models.News;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private NewsPagerAdapter adapterViewPager;
    ArrayList<News> mNewses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        //We pull out our ArrayList<News> Parcelable using the unwrap() method
        mNewses = Parcels.unwrap(getIntent().getParcelableExtra("newses"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new NewsPagerAdapter(getSupportFragmentManager(), mNewses);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
