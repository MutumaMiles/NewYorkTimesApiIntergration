package com.goodwill.apainterviewapp.remote;


import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("7.json")
    Call<JsonObject> getArticle(@Query("api-key") String apiKey);
}

