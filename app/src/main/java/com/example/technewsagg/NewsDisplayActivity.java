package com.example.technewsagg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.technewsagg.NewsFinders.NewsFinder;
import com.example.technewsagg.NewsFinders.PlayTechNewsFinder;
import com.example.technewsagg.NewsFinders.SlashGearNewsFinder;
import com.example.technewsagg.NewsFinders.TechRadarNewsFinder;
import com.example.technewsagg.NewsFinders.TheIndianExpressNewsFinder;
import com.example.technewsagg.NewsFinders.TheVergeNewsFinder;
import com.example.technewsagg.NewsFinders.TomsHardwareNewsFinder;
import com.example.technewsagg.NewsFinders.VentureBeatNewsFinder;
import com.example.technewsagg.NewsFinders.YodaNewsFinder;
import com.example.technewsagg.NewsFinders.ZonaITNewsFinder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NewsDisplayActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private String preferences="USERS_SUBSCRIPTIONS";
    private String currentUser;
    private UserSettings settings;
    private List<NewsFinder> finders= Arrays.asList(new TheVergeNewsFinder(),new TomsHardwareNewsFinder(),new VentureBeatNewsFinder(),new ZonaITNewsFinder()
            ,new YodaNewsFinder(),new PlayTechNewsFinder(),new TheIndianExpressNewsFinder(),new SlashGearNewsFinder(),new TechRadarNewsFinder());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_display);
        Intent intent=getIntent();
        currentUser=intent.getStringExtra("currentUser");
        settings=getSetting();
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        ArrayList<NewsFinder> finders=prepareFinders();
        ArrayList<ArrayList<NewsArticle>> arrayLists=prepareLists(finders);
        for(int i=0;i<finders.size();i++)
        {
            finders.get(i).retrieveArticles(arrayLists.get(i),requestQueue);
        }
        prog(getApplicationContext(),arrayLists);
    }

    public void prog(final Context context, final ArrayList<ArrayList<NewsArticle>> list)
    {
        final Timer t=new Timer();
        TimerTask tt=new TimerTask() {
            @Override
            public void run() {
                boolean noEmptyLists=true;
                for(List<NewsArticle> element:list)
                {
                    if(element.size()==0)
                    {
                        noEmptyLists=false;
                    }
                }
                if(noEmptyLists)
                {
                    List<NewsArticle> allArticles=new ArrayList<NewsArticle>();
                    for(List<NewsArticle> element:list)
                    {
                        allArticles.addAll(element);
                    }
                    Collections.shuffle(allArticles);
                    t.cancel();
                    Intent intent=new Intent(context,ArticlesDisplayActivity.class);
                    intent.putParcelableArrayListExtra("Articles",(ArrayList<NewsArticle>)allArticles);
                    startActivity(intent);
                }
            }
        };
        t.schedule(tt,0,100);
    }

    private ArrayList<ArrayList<NewsArticle>> prepareLists(List<NewsFinder> finders)
    {
        ArrayList<ArrayList<NewsArticle>> lists=new ArrayList<ArrayList<NewsArticle>>();
        for(int i=0;i<finders.size();i++)
        {
            lists.add(new ArrayList<NewsArticle>());
        }
        return lists;
    }

    private UserSettings getSetting( )
    {
        SharedPreferences pref=getSharedPreferences(preferences,MODE_PRIVATE);
        Gson gson=new Gson();
        String json=pref.getString(currentUser,null);
        Type type=new TypeToken<UserSettings>(){}.getType();
        UserSettings settings=gson.fromJson(json,type);
        return settings;
    }
    private ArrayList<NewsFinder> prepareFinders()
    {
        ArrayList<NewsFinder> desiredFinders=new ArrayList<NewsFinder>();
        for(NewsFinder finder:finders)
        {
            if(settings.getValue(finder.toString()))
            {
                desiredFinders.add(finder);
            }
        }
        return desiredFinders;
    }
}
