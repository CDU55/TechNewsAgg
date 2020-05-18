package com.example.technewsagg;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private AccountsHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.username=(EditText)findViewById(R.id.usernameField);
        this.password=(EditText)findViewById(R.id.passwordField);
        askPermissions();
        databaseHelper=new AccountsHelper(this);
    }
    public void goToRegister(View view)
    {
        Intent switchToRegister=new Intent(this,RegisterActivity.class);
        startActivity(switchToRegister);
    }


    public void login(View view)
    {
        String user=this.username.getText().toString();
        String pass=this.password.getText().toString();
        if(databaseHelper.validAccount(user.trim(),pass.trim()))
        {
            switchToUserMenu();
        }
        else
        {
            Toast.makeText(this,"Invalid credentials",Toast.LENGTH_SHORT).show();
        }

    }

    public void switchToUserMenu()
    {
        Intent switchToRegister=new Intent(this,UserMenuActivity.class);
        switchToRegister.putExtra("currentUser",this.username.getText().toString());
        startActivity(switchToRegister);
    }
    public void askPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    2);
        }
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
}
