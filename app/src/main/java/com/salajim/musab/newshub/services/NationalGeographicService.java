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

public class NationalGeographicService {

    // The following method build, signs, and sends an OAuth API request using OkHttp and Signpost:
    public static void finNationalGeographicNews(String articles, Callback callback) {

        //Here we create OkHttpClient to send and create requests
        OkHttpClient client = new OkHttpClient.Builder().build();
        //Building a new URL
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.NATIONAL_GEOGRAPHIC_URL).newBuilder();//Creates a new URL
        urlBuilder.addQueryParameter(Constants.QUERY_APIKEY_HOLDER, Constants.ApiKey);

        //Turns the finished URL into a String
        String url = urlBuilder.build().toString();
        //Makes a new request with OkHttp using the new URL
        Request request = new Request.Builder()
                .url(url)
                .build();

        //We creat a Call object and place request in it
        Call call = client.newCall(request);
        call.enqueue(callback);//Then we excute the request
    }

    //This method returns an array of NationalGeographic newsw
    public ArrayList<News> processResults(Response response) {
        ArrayList<News> newses = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if(response.isSuccessful()) {
                JSONObject newsesJSON = new JSONObject(jsonData);
                JSONArray articlesJSON = newsesJSON.getJSONArray("articles");
                for(int i = 0; i < articlesJSON.length(); i++) {
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
