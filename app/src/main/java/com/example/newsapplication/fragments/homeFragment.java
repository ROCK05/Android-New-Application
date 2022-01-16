package com.example.newsapplication.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.example.newsapplication.ModelClass;
import com.example.newsapplication.R;
import com.example.newsapplication.mainNews;
import com.example.newsapplication.newsAdapter;
import com.example.newsapplication.utilities;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class homeFragment extends Fragment {

// arjunMajithiya
     String apiKey = "fb7ad87d4c0a4e51af23b8caa9ea8248";

    //201901051
    //String apiKey = "d4a32a24d6cc4022bd5c62bafdac57ae";
    ArrayList<ModelClass> modelClassArrayList;
    newsAdapter adapter;
    public static String country = "in";
    private RecyclerView recyclerViewHome;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, null);
        recyclerViewHome = view.findViewById(R.id.recyclerViewHome);
        modelClassArrayList = new ArrayList<ModelClass>();
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new newsAdapter(getContext(), modelClassArrayList);
        recyclerViewHome.setAdapter(adapter);

        findNews();
        return view;
    }


    private void findNews(){
        utilities.getApiInterface().getMainNews(country, 10, apiKey).enqueue(new Callback<mainNews>() {
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