package com.example.technewsagg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SubscriptionsActivity extends AppCompatActivity {

    private ArrayList<PubSubDisplay> subscriptions;
    private RecyclerView recyclerView;
    private SubDisplayAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String currentUser;
    private UserSettings settings;
    private String preferences="USERS_SUBSCRIPTIONS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriptions);
        Intent intent=getIntent();
        currentUser=intent.getStringExtra("currentUser");
        this.settings=getSetting();
        final ArrayList<PubSubDisplay> subscriptions=PubSubDisplay.getPublications(settings);

        recyclerView=findViewById(R.id.subscriptionView);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        adapter=new SubDisplayAdapter(subscriptions);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SubDisplayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                subscriptions.get(position).flip();
                settings.flip(subscriptions.get(position).publicationName);
                adapter.notifyItemChanged(position);
            }
        });

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

    private void saveSettings( )
    {
        SharedPreferences pref=getSharedPreferences(preferences,MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        Gson gson=new Gson();
        String json=gson.toJson(settings);
        editor.putString(currentUser,json);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        saveSettings();
        super.onBackPressed();
    }
}
