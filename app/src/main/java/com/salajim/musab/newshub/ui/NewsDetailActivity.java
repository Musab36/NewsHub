package com.salajim.musab.newshub.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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

        //We setup the Pager Adapter
        adapterViewPager = new NewsPagerAdapter(getSupportFragmentManager(), mNewses);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }

    //Menus inflated
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //Actions for the inflated menus and thier intents
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.general_news) {
            Intent intent = new Intent(NewsDetailActivity.this, GeneralNewsActivity.class);
            startActivity(intent);
        }
        if(id == R.id.tech_news) {
            Intent intent = new Intent(NewsDetailActivity.this, TechnologyNewsActivity.class);
            startActivity(intent);
        }
        if(id == R.id.business_news) {
            Intent intent = new Intent(NewsDetailActivity.this, BusinessNewsActivity.class);
            startActivity(intent);
        }
        if(id == R.id.entertainment) {
            Intent intent = new Intent(NewsDetailActivity.this, EntertainmentNewsActivity.class);
            startActivity(intent);
        }
        if(id == R.id.nationalGeographicNews) {
            Intent intent = new Intent(NewsDetailActivity.this, NationalGeographicActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
