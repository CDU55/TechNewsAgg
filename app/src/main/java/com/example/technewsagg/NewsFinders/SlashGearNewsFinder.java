package com.example.technewsagg.NewsFinders;

import com.example.technewsagg.NewsFinders.NewsFinder;
import com.example.technewsagg.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SlashGearNewsFinder extends NewsFinder {

    public SlashGearNewsFinder() {
        super();
        this.baseURl="https://www.slashgear.com/topics/tech/";
        this.publicationName="Slash Gear";
        this.logoID= R.drawable.slash_gear_logo;

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
        Matcher m= Pattern.compile("<h2 class=\"title\"><a href=\".+?\">.+?</a>").matcher(url);
        while(m.find())
        {
            articles.add(m.group());
        }
        return articles;
    }

    @Override
    public String retrieveTitle(String article) {
        String title=new String();
        Matcher m=Pattern.compile("<a href=\"(.+?)\">(.+?)</a>").matcher(article);
        if(m.find())
        {
            title=m.group(2);
        }
        return title.replaceAll("&#82[0-9][0-9];","");
    }
}
