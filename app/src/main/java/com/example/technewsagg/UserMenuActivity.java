package com.example.technewsagg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserMenuActivity extends AppCompatActivity {

    private String currentUser;
    private TextView greetMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        this.greetMessage=(TextView)findViewById(R.id.userGreetMessage);
        Intent intent=getIntent();
        currentUser=intent.getStringExtra("currentUser");
        this.greetMessage.setText("Hello "+currentUser);
    }

    public void goToNews(View view)
    {
        Intent intent=new Intent(this,NewsDisplayActivity.class);
        intent.putExtra("currentUser",currentUser);
        startActivity(intent);
    }

    public void goToSubscriptions(View view)
    {
        Intent intent=new Intent(this,SubscriptionsActivity.class);
        intent.putExtra("currentUser",currentUser);
        startActivity(intent);
    }
}
