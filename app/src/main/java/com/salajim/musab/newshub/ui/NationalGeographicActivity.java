package com.salajim.musab.newshub.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.salajim.musab.newshub.R;
import com.salajim.musab.newshub.adapters.NewsListAdapter;
import com.salajim.musab.newshub.models.News;
import com.salajim.musab.newshub.services.NationalGeographicService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NationalGeographicActivity extends AppCompatActivity {
    public static final String TAG = NationalGeographicActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private NewsListAdapter mAdapter;

    public ArrayList<News> mNewses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_national_geographic);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String news = intent.getStringExtra("news");

        getNationalGeographicNews(news);
    }

    //This method retrieves the data from the news API and displays it
    public void getNationalGeographicNews(String newses) {
        final NationalGeographicService nationalGeographicService = new NationalGeographicService();
        nationalGeographicService.finNationalGeographicNews(newses, new Callback() {

            //This method runs if the request fails
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            //This method runs if the request fails
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                mNewses = nationalGeographicService.processResults(response);

                //We are switching to the main thread
                NationalGeographicActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        //We then set the adapter and the LayoutManager required by the RecyclerView
                        mAdapter = new NewsListAdapter(getApplicationContext(), mNewses);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NationalGeographicActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
