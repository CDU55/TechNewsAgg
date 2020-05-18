package com.example.technewsagg.NewsFinders;

import com.example.technewsagg.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TechSpotNewsFinder extends NewsFinder {
    public TechSpotNewsFinder() {
        super();
        this.baseURl="https://www.techspot.com/trending/";
        this.publicationName="Tech Spot";
        this.logoID= R.drawable.tech_spot_logo;

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
        Matcher m= Pattern.compile("\"<h2 >\\s*<a href=\".+?\" >\\s*.+?</a>\\s*</h2>").matcher(url);
        while(m.find())
        {
            articles.add(m.group());
        }
        return articles;
    }

    @Override
    public String retrieveTitle(String article) {
        String title=new String();
        Matcher m=Pattern.compile(">\\s*(.+?)</a>").matcher(article);
        if(m.find())
        {
            title=m.group(1);
        }
        return title.replaceAll("&#82[0-9][0-9];","").replaceAll("&nbsp;"," ");
    }
}
