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

public class ScienceNewsService {


    // The following method build, signs, and sends an OAuth API request using OkHttp and Signpost
    public static void findScienceNews(String articles, Callback callback) {

        // Here we are creating OKHttpClient to create and send request
        OkHttpClient client = new OkHttpClient.Builder().build();
        // Building the request URL with OKHttp
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.SCIENCE_NEWS_URL).newBuilder();//Creating new url
        urlBuilder.addQueryParameter(Constants.QUERY_APIKEY_HOLDER, Constants.ApiKey);

        //Turns the finished url into a string
        String url = urlBuilder.build().toString();

        //Creating new request with OkHttp using the new url
        Request request = new Request.Builder()
                .url(url)
                .build();

        //Excuting the request
        Call call = client.newCall(request);//Created a Call object and place the request in it
        call.enqueue(callback);//Then excute the request
    }

    //This method returns an arry of science news
    public ArrayList<News> processResults(Response response) {
        ArrayList<News> newses = new ArrayList<>();

        try {
            // Transforms the API response into a String in order to double-check the response was successful
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
