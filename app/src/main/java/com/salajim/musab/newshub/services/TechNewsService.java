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

public class TechNewsService {

    public static void findTechNews(String articles, Callback callback) {

        //SignPost
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.TECH_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.QUERY_APIKEY_HOLDER, Constants.ApiKey);

        //Turning the finished url into a string
        String url = urlBuilder.build().toString();
        //Creates a request with the stringfied url
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
                for(int i = 0; i < articlesJSON.length(); i++) {
                    JSONObject newJSON = articlesJSON.getJSONObject(i);
                    String author = newJSON.getString("author");
                    String title = newsesJSON.getString("title");
                    String description = newsesJSON.getString("description");
                    String url = newsesJSON.getString("url");
                    String urlToImage = newJSON.getString("urlToImage");
                    String publishedAt = newJSON.getString("publishedAt");

                    News news = new News(title, author, description, url, urlToImage, publishedAt);
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
