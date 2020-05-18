package com.example.technewsagg.NewsFinders;

import com.example.technewsagg.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TheVergeNewsFinder extends NewsFinder {
    public TheVergeNewsFinder() {
        super();
        this.baseURl="https://www.theverge.com/tech/archives";
        this.publicationName="The Verge";
        this.logoID= R.drawable.the_verge_logo_2016;
    }

    public List<String> findArticles(String html)
    {
        List<String> articles=new ArrayList<String>();
        Matcher m= Pattern.compile("<h2 class=\"c-entry-box--compact__title\"><a data-chorus-optimize-field=\"hed\" data-analytics-link=\"article\" .+?</a></h2>").matcher(html);
        while(m.find())
        {
            articles.add(m.group());
        }
        return articles;
    }

    public String retrieveTitle(String article)
    {
        String title=new String();
        Matcher m=Pattern.compile(">.+?</a>").matcher(article);
        if(m.find())
        {
            title=m.group();
        }
        title=title.replaceAll("<.+?>","").replace(">","");
        return title.replaceAll("&#82[0-9][0-9];","");
    }

    public String retrieveArticleURL(String article)
    {
        String articleUrl=new String();
        Matcher m=Pattern.compile("href=\".+\"").matcher(article);
        if(m.find())
        {
            articleUrl=m.group();
        }
        articleUrl=articleUrl.substring(6);
        articleUrl=articleUrl.substring(0,articleUrl.length()-1);
        return articleUrl;
    }


}
