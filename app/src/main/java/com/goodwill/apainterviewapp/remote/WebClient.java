package com.goodwill.apainterviewapp.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebClient {
    public static final String BASE_URL = "https://api.nytimes.com/svc/mostpopular/v2/emailed/";

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = builder.build();

    //Service builder that creates implementation of those interfaces is created here.
    public static <T> T buildService(Class<T> type) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();
        builder.client(client);
        return builder.build().create(type);
    }
}
