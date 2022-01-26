package com.example.newsapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class searchNewsView extends AppCompatActivity {
    // arjunMajithiya
    String apiKey = "fb7ad87d4c0a4e51af23b8caa9ea8248";

    //201901051
    //String apiKey = "d4a32a24d6cc4022bd5c62bafdac57ae";
    ArrayList<ModelClass> modelClassArrayList;
    newsAdapter adapter;
    //public static String country = "in";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_news_view);

        Bundle extras = getIntent().getExtras();
        String Query = "";
        if (extras != null) {
            Query = extras.getString("QueryText");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.searchToolbar);
        toolbar.setTitle(Query);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerViewSearch = findViewById(R.id.recyclerViewSearch);
        modelClassArrayList = new ArrayList<ModelClass>();
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(this));
        adapter = new newsAdapter(this, modelClassArrayList);
        recyclerViewSearch.setAdapter(adapter);
        findNews(Query);
    }

    private void findNews(String query){
        utilities.getApiInterface().getSearchNews(query, "en",10, apiKey).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(@NonNull Call<mainNews> call, @NonNull Response<mainNews> response) {
                if(response.isSuccessful())
                {
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<mainNews> call, @NonNull Throwable t) {

            }
        });


    }
}