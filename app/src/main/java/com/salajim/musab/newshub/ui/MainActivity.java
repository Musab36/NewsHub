package com.salajim.musab.newshub.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.salajim.musab.newshub.Constants;
import com.salajim.musab.newshub.R;
import com.salajim.musab.newshub.adapters.NewsListAdapter;
import com.salajim.musab.newshub.models.News;
import com.salajim.musab.newshub.services.NewsService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private NewsListAdapter mAdapter;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentNews;

    public ArrayList<News> mNewses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String news = intent.getStringExtra("news");

        getNews(news);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentNews = mSharedPreferences.getString(Constants.PREFERENCES_NEWS_KEY, null);

        if(mRecentNews != null) {
            getNews(mRecentNews);
        }

    }

    // Menus inflated here
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        inflater.inflate(R.menu.menu_share, menu);
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String source) {
                addToSharedPreferences(source);
                getNews(source);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    // Menu actions Listeners
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hey, please download this cool app!");
            startActivity(shareIntent);
            return true;
        }
        if(id == R.id.general_news) {
            Intent intent = new Intent(MainActivity.this, GeneralNewsActivity.class);
            startActivity(intent);
        }
        if(id == R.id.tech_news) {
            Intent intent = new Intent(MainActivity.this, TechnologyNewsActivity.class);
            startActivity(intent);
        }
        if(id == R.id.business_news) {
            Intent intent = new Intent(MainActivity.this, BusinessNewsActivity.class);
            startActivity(intent);
        }
        if(id == R.id.entertainment) {
            Intent intent = new Intent(MainActivity.this, EntertainmentNewsActivity.class);
            startActivity(intent);
        }
        if(id == R.id.nationalGeographicNews) {
            Intent intent = new Intent(MainActivity.this, NationalGeographicActivity.class);
            startActivity(intent);
        }
        if(id == R.id.scienceNews) {
            Intent intent = new Intent(MainActivity.this, ScienceNewsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    // Callback method for desplaying response data
    public void getNews(String newses) {
        final NewsService newsService = new NewsService();
        NewsService.findNews(newses, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mNewses = newsService.processResults(response);

                MainActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new NewsListAdapter(getApplicationContext(), mNewses);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }

    private void addToSharedPreferences(String news) {
        mEditor.putString(Constants.PREFERENCES_NEWS_KEY, news).apply();
    }
}
