package com.example.technewsagg;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsArticle implements Parcelable {

    public String title;
    public String author;
    public String URL;
    public int authorLogo;

    public NewsArticle(String title,String author,String URL,int authorLogo)
    {
        this.title=title;
        this.author=author;
        this.URL=URL;
        this.authorLogo=authorLogo;
    }

    protected NewsArticle(Parcel in) {
        title = in.readString();
        author = in.readString();
        URL = in.readString();
        this.authorLogo=in.readInt();
    }

    public static final Creator<NewsArticle> CREATOR = new Creator<NewsArticle>() {
        @Override
        public NewsArticle createFromParcel(Parcel in) {
            return new NewsArticle(in);
        }

        @Override
        public NewsArticle[] newArray(int size) {
            return new NewsArticle[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(URL);
        dest.writeInt(authorLogo);

    }
}
