package com.example.technewsagg;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubDisplayAdapter extends RecyclerView.Adapter<SubDisplayAdapter.SubDisplayViewHolder> {

    private ArrayList<PubSubDisplay> subDisplays;
    private OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener=listener;
    }
    public static class SubDisplayViewHolder extends RecyclerView.ViewHolder{

        public ImageView logo;
        public TextView publicatioName;
        public TextView subscribed;
        public SubDisplayViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            logo=itemView.findViewById(R.id.publicationLogo);
            publicatioName=itemView.findViewById(R.id.publicationName);
            subscribed=itemView.findViewById(R.id.subscribedToPublication);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }

                }
            });
        }


    }

    public SubDisplayAdapter(ArrayList<PubSubDisplay> subscriptions)
    {
        this.subDisplays=subscriptions;
    }

    @NonNull
    @Override
    public SubDisplayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.subscription_display,parent,false);
        SubDisplayViewHolder holder=new SubDisplayViewHolder(view,this.listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubDisplayViewHolder holder, int position) {

        PubSubDisplay currentItem=this.subDisplays.get(position);
        holder.logo.setImageResource(currentItem.logoID);
        holder.publicatioName.setText(currentItem.publicationName);
        if(currentItem.subscribed)
        {
            holder.subscribed.setText("Subscribed");
            holder.subscribed.setTextColor(Color.parseColor("#06A77D"));
        }
        else
        {
            holder.subscribed.setText("Not Subscribed");
            holder.subscribed.setTextColor(Color.parseColor("#CC2936"));
        }

    }

    @Override
    public int getItemCount() {
        return this.subDisplays.size();
    }
}
