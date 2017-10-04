package com.salajim.musab.newshub.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.salajim.musab.newshub.R;
import com.salajim.musab.newshub.adapters.NewsListAdapter;
import com.salajim.musab.newshub.models.News;
import com.salajim.musab.newshub.services.EntertainmentService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class EntertainmentNewsActivity extends AppCompatActivity {
    public static final String TAG = EntertainmentNewsActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private NewsListAdapter mAdapter;

    public ArrayList<News> mNewses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment_news);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String news = intent.getStringExtra("news");

        getEntertainmentNews(news);
    }

    public void getEntertainmentNews(String newses) {
        final EntertainmentService entertainmentService = new EntertainmentService();
        entertainmentService.findEntertainmentNews(newses, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mNewses = entertainmentService.processResults(response);

                EntertainmentNewsActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new NewsListAdapter(getApplicationContext(), mNewses);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(EntertainmentNewsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
