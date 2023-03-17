package com.example.champion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class Onboarding extends AppCompatActivity {

    public static String avatar;
    public static String nickName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        SharedPreferences prefs = this.getSharedPreferences(
                "Date", Context.MODE_PRIVATE);
        if(prefs != null)
        {
            if(!prefs.getString("NickName", "").equals(""))
            {
                avatar = prefs.getString("Avatar", "");
                nickName = prefs.getString("NickName", "");
                startActivity(new Intent(this, MainActivity.class));
            }
        }
    }

    public void nextRegistrarion(View v)
    {
        startActivity(new Intent(this, Register.class));
    }

    public void nextLogin(View v)
    {
        startActivity(new Intent(this, Login.class));
    }
}