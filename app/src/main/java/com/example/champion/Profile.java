package com.example.champion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.champion.Adapter.AdapterMaskMeditation;

public class Profile extends AppCompatActivity {

    TextView tvName;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvName = findViewById(R.id.tvName);
        tvName.setText(Onboarding.nickName);
        image = findViewById(R.id.avatar);
        new AdapterMaskMeditation.DownloadImageTask((ImageView) image)
                .execute(Onboarding.avatar);
    }

    public  void Menu(View view) {
        startActivity(new Intent(this, Menu.class));
    }

    public void Login(View view) {
        SharedPreferences prefs = getSharedPreferences(
                "Date", Context.MODE_PRIVATE);
        prefs.edit().putString("Avatar", "").apply();
        prefs.edit().putString("NickName", "").apply();

        startActivity(new Intent(this, Login.class));
    }

    public  void Home(View view)
    {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void Listen(View view)
    {
        startActivity(new Intent(this, Listen.class));
    }


}