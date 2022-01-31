package com.example.newsapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    String Base_url = "https://newsapi.org/v2/";

    @GET("top-headlines")
    Call<mainNews> getMainNews(
            @Query("country") String country,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String apiKey

    );

    @GET("top-headlines")
    Call<mainNews> getCategoryNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String apiKey

    );

    @GET("everything")
    Call<mainNews> getWorldNews(
            @Query("q") String q,
            @Query("from") String date,
            @Query("language") String language,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String apiKey
    );

    @GET("everything")
    Call<mainNews> getSearchNews(
            @Query("q") String q,
            @Query("language") String language,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String apiKey
    );
}
