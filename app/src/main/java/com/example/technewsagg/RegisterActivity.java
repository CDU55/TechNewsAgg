package com.example.technewsagg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private AccountsHelper databaseHelper;
    private String preferences="USERS_SUBSCRIPTIONS";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper=new AccountsHelper(this);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("username",username.getText().toString());
        outState.putString("password",password.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        username.setText(savedInstanceState.getString("username"));
        password.setText(savedInstanceState.getString("password"));
    }

    public void registerUser(View view)
    {
        username=(EditText)findViewById(R.id.usernameInput);
        password=(EditText)findViewById(R.id.passwordInput);
        String username_data=username.getText().toString().trim();
        String password_data=password.getText().toString().trim();
        if(username_data.length()<3)
        {
            Toast.makeText(this,"The username must be at least 3 characters",Toast.LENGTH_SHORT).show();
        }
        if(!username_data.matches("[a-zA-Z]{3,}"))
        {
            Toast.makeText(this,"The username must contain only letters",Toast.LENGTH_SHORT).show();
        }
        else if(password_data.length()<5)
        {
            Toast.makeText(this,"Password must be at least 5 characters",Toast.LENGTH_SHORT).show();
        }
        else if(!password_data.matches("[a-zA-Z0-9]{5,}"))
        {
            Toast.makeText(this,"The password must contain only letters or numbers",Toast.LENGTH_SHORT).show();
        }
        else if(databaseHelper.usernameExists(username_data))
        {
            Toast.makeText(this,"User already exists",Toast.LENGTH_SHORT).show();
        }
        else
        {
            databaseHelper.createAccount(username_data,password_data);
            SharedPreferences pref=getSharedPreferences(preferences,MODE_PRIVATE);
            SharedPreferences.Editor editor=pref.edit();
            Gson gson=new Gson();
            String json=gson.toJson(new UserSettings());
            editor.putString(username_data,json);
            editor.apply();
            Toast.makeText(this,"Account created",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
}
