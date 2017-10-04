package com.salajim.musab.newshub.services;


import com.salajim.musab.newshub.Constants;
import com.salajim.musab.newshub.models.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GeneralNewsService {

    public static void findGeneralNews(String articles, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.GENERAL_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.QUERY_APIKEY_HOLDER, Constants.ApiKey);

        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<News> processResults(Response response) {
        ArrayList<News> newses = new ArrayList<>();

        try {
            String jsonData = response.body().string();

            if(response.isSuccessful()) {
                JSONObject newsesJSON = new JSONObject(jsonData);
                JSONArray articlesJSON = newsesJSON.getJSONArray("articles");
                for(int i = 0; i < articlesJSON.length(); i ++) {
                    JSONObject newsJSON = articlesJSON.getJSONObject(i);
                    String author = newsJSON.getString("author");
                    String title = newsJSON.getString("title");
                    String description = newsJSON.getString("description");
                    String url = newsJSON.getString("url");
                    String urlToImage = newsJSON.getString("urlToImage");
                    String publishedAt = newsJSON.getString("publishedAt");

                    News news = new News(author, title, description, url, urlToImage, publishedAt);
                    newses.add(news);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newses;
    }
}
