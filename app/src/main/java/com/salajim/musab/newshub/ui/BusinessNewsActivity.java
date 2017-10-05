package com.salajim.musab.newshub.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.salajim.musab.newshub.R;
import com.salajim.musab.newshub.adapters.NewsListAdapter;
import com.salajim.musab.newshub.models.News;
import com.salajim.musab.newshub.services.BusinessService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BusinessNewsActivity extends AppCompatActivity {
    public static final String TAG = BusinessNewsActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private NewsListAdapter mAdapter;

    public ArrayList<News> mNewses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_news);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String news = intent.getStringExtra("news");

        getBusinessNews(news);
    }

    //This method retrieves the data from the news API
    public void getBusinessNews(String newses) {
        final BusinessService businessService = new BusinessService();
        businessService.findBusinessNews(newses, new Callback() {

            //This method runs if the request fails
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            //This method runs if the request is succesful
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mNewses = businessService.processResults(response);

                //We changing to the main thread with Runnable() method
                BusinessNewsActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        //We set the adapter and the LayoutManager
                        mAdapter = new NewsListAdapter(getApplicationContext(), mNewses);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BusinessNewsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
