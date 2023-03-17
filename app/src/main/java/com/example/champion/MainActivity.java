package com.example.champion;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.champion.Adapter.AdapterMaskFeel;
import com.example.champion.Adapter.AdapterMaskMeditation;
import com.example.champion.Mask.MaskFeel;
import com.example.champion.Mask.MaskMeditation;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AdapterMaskMeditation pAdapter;
    private List<MaskMeditation> listQuote = new ArrayList<>();
    private AdapterMaskFeel dataRVAdapter;
    private List<MaskFeel> listFeeling = new ArrayList<>();

    ImageView imgProfile;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView ivProducts = findViewById(R.id.lvBlock);
        pAdapter = new AdapterMaskMeditation(MainActivity.this, listQuote);
        ivProducts.setAdapter(pAdapter);
        new GetQuotes().execute();
        RecyclerView rvFeeling = findViewById(R.id.recyclerView);
        rvFeeling.setHasFixedSize(true);
        rvFeeling.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        dataRVAdapter = new AdapterMaskFeel(listFeeling, MainActivity.this);
        rvFeeling.setAdapter(dataRVAdapter);
        new GetFeel().execute();

        imgProfile = findViewById(R.id.ivProfile);
        new AdapterMaskMeditation.DownloadImageTask((ImageView) imgProfile)
                .execute(Onboarding.avatar);
        text = findViewById(R.id.back);
        text.setText(text.getText().toString() + Onboarding.nickName + "!");
    }

    private class GetQuotes extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("http://mskko2021.mad.hakta.pro/api/quotes");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                }
                return result.toString();
            }
            catch (Exception exception)
            {
                return null;
            }
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try
            {
                listQuote.clear();
                pAdapter.notifyDataSetInvalidated();

                JSONObject object = new JSONObject(s);
                JSONArray tempArray  = object.getJSONArray("data");

                for (int i = 0;i<tempArray.length();i++)
                {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    MaskMeditation tempProduct = new MaskMeditation(
                            productJson.getInt("id"),
                            productJson.getString("title"),
                            productJson.getString("image"),
                            productJson.getString("description")
                    );
                    listQuote.add(tempProduct);
                    pAdapter.notifyDataSetInvalidated();
                }

            }
            catch (Exception exception)
            {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class GetFeel extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("http://mskko2021.mad.hakta.pro/api/feelings");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                }
                return result.toString();
            }
            catch (Exception exception)
            {
                return null;
            }
        }
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try
            {
                listFeeling.clear();
                dataRVAdapter.notifyDataSetChanged();
                JSONObject object = new JSONObject(s);
                JSONArray tempArray  = object.getJSONArray("data");
                for (int i = 0;i<tempArray.length();i++)
                {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    MaskFeel tempProduct = new MaskFeel(
                            productJson.getInt("id"),
                            productJson.getString("title"),
                            productJson.getString("image"),
                            productJson.getInt("position")
                    );
                    listFeeling.add(tempProduct);
                    dataRVAdapter.notifyDataSetChanged();
                }
                listFeeling.sort(Comparator.comparing(MaskFeel::getPosition));
                dataRVAdapter.notifyDataSetChanged();
            }
            catch (Exception exception)
            {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public  void Menu(View view)
    {
        startActivity(new Intent(this, Menu.class));
    }

    public  void Profile(View view)
    {
        startActivity(new Intent(this, Profile.class));
    }

    public void Listen(View view)
    {
        startActivity(new Intent(this, Listen.class));
    }
}