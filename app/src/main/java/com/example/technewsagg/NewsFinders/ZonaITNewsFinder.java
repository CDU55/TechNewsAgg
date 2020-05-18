package com.example.technewsagg.NewsFinders;

import com.example.technewsagg.NewsFinders.NewsFinder;
import com.example.technewsagg.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZonaITNewsFinder extends NewsFinder {
    public ZonaITNewsFinder() {
        super();
        this.baseURl="https://zonait.ro/";
        this.publicationName="Zona IT";
        this.logoID= R.drawable.zona_it_logo;

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
        Matcher m= Pattern.compile("<li class=\"news-item\">\\s*<a href=\".+?\">.+?</a>\\s*</li>").matcher(url);
        while(m.find())
        {
            articles.add(m.group());
        }
        return articles;
    }

    @Override
    public String retrieveTitle(String article) {
        String title=new String();
        Matcher m=Pattern.compile("\">(.+?)</a>").matcher(article);
        if(m.find())
        {
            title=m.group(1);
        }
        return title.replaceAll("&#82[0-9][0-9];","");
    }
}
