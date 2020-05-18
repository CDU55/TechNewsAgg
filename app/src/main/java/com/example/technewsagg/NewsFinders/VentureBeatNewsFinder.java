package com.example.technewsagg.NewsFinders;

import com.example.technewsagg.NewsFinders.NewsFinder;
import com.example.technewsagg.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VentureBeatNewsFinder extends NewsFinder {
    public VentureBeatNewsFinder() {
        super();
        this.baseURl="https://venturebeat.com/";
        this.publicationName="VentureBeat";
        this.logoID= R.drawable.venture_beat_logo;

    }

    @Override
    public String retrieveArticleURL(String article) {

        String articleUrl=new String();
        Matcher m= Pattern.compile("href=\"(.+?)\"").matcher(article);
        if(m.find())
        {
            articleUrl=m.group(1);
        }
        return articleUrl;
    }

    @Override
    public List<String> findArticles(String url) {
        List<String> articles=new ArrayList<String>();
        Matcher m= Pattern.compile("href=\".+?\" title=\".+?\" rel=\"bookmark\" class=\"ArticleListing__title-link\">").matcher(url);
        while(m.find())
        {
            articles.add(m.group());
        }
        return articles;
    }

    @Override
    public String retrieveTitle(String article) {
        String title=new String();
        Matcher m=Pattern.compile("title=\"(.+?)\"").matcher(article);
        if(m.find())
        {
            title=m.group(1);
        }
        return title.replaceAll("&#82[0-9][0-9];","");
    }
}
