package com.example.technewsagg.NewsFinders;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.technewsagg.NewsArticle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class NewsFinder {

    protected String baseURl;
    protected int logoID;
    protected String publicationName;

    public NewsFinder()
    {

    }

    public abstract String retrieveArticleURL(String article);
    public abstract List<String> findArticles(String url);
    public abstract String retrieveTitle(String article);

    public void retrieveArticles(final List<NewsArticle> art, RequestQueue requestQueue)
    {
        final StringRequest request=new StringRequest(Request.Method.GET, this.baseURl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    List<NewsArticle> articles=retrieveArticlesFromPage(response);
                    art.addAll(articles);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }

    public List<NewsArticle> retrieveArticlesFromPage(String html)
    {
        List<NewsArticle> articles=new ArrayList<NewsArticle>();
        List<String> rawArticles=findArticles(html);
        for(String article:rawArticles)
        {
            articles.add(new NewsArticle(retrieveTitle(article),this.publicationName,retrieveArticleURL(article),this.logoID));
        }
        return articles;
    }

    @NonNull
    @Override
    public String toString() {
        return this.publicationName;
    }
}
