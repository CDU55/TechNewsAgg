package com.example.technewsagg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> implements Filterable {

    private ArrayList<NewsArticle> articles;
    private ArrayList<NewsArticle> allArticles;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {

        this.listener=listener;
    }
    public static class ArticleViewHolder extends RecyclerView.ViewHolder{

        public ImageView logo;
        public TextView author;
        public TextView title;

        public ArticleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            logo=itemView.findViewById(R.id.techPublicationLogo);
            author=itemView.findViewById(R.id.articleAuthor);
            title=itemView.findViewById(R.id.articleTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener !=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }

                }
            });
        }
    }
    public ArticleAdapter(ArrayList<NewsArticle> articles)
    {
        this.articles=articles;
        this.allArticles=new ArrayList<NewsArticle>();
        this.allArticles.addAll(articles);
    }
    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_article,parent,false);
        ArticleViewHolder arv=new ArticleViewHolder(view,listener);
        return arv;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {

        NewsArticle currentArticle=this.articles.get(position);
        holder.title.setText(currentArticle.title);
        holder.author.setText(currentArticle.author);
        holder.logo.setImageResource(currentArticle.authorLogo);

    }

    @Override
    public int getItemCount() {
        return this.articles.size();
    }

    public Filter getFilter()
    {
        return articlesFilter;
    }

    private Filter articlesFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<NewsArticle> filteredArticles=new ArrayList<NewsArticle>();
            if(constraint==null || constraint.length()==0)
            {
                filteredArticles.addAll(allArticles);
            }
            else
            {
                String pattern=constraint.toString().toLowerCase().trim();

                for(NewsArticle article : allArticles)
                {
                    if(article.title.toLowerCase().contains(pattern))
                    {
                        filteredArticles.add(article);
                    }
                }
            }

            FilterResults results=new FilterResults();
            results.values=filteredArticles;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            articles.clear();
            articles.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}
