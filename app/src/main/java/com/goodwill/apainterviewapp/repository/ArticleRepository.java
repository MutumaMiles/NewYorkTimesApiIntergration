package com.goodwill.apainterviewapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.goodwill.apainterviewapp.models.Article;
import com.goodwill.apainterviewapp.models.ErrorData;
import com.goodwill.apainterviewapp.remote.ApiService;
import com.goodwill.apainterviewapp.remote.WebClient;
import com.goodwill.apainterviewapp.utils.Constants;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {
    private static ApiService apiService;
    private static ArticleRepository articleRepository;
    private MutableLiveData<List<Article>> articleMutableLiveData;
    private MutableLiveData<ErrorData> errorDataLiveData;

    private ArticleRepository() {
        articleMutableLiveData = new MutableLiveData<>();
        errorDataLiveData = new MutableLiveData<>();
    }

    public static ArticleRepository getInstance() {
        apiService = WebClient.buildService(ApiService.class);

        if (articleRepository == null)
            articleRepository = new ArticleRepository();

        return articleRepository;
    }

    /**
     * load Articles from New York Times
     */
    public void loadArticles() {
        Call<JsonObject> call = apiService.getArticle(Constants.API_KEY);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response != null){
                    if(response.body() !=null){
                        JsonObject object = response.body();
                        parseJson(object);
                    }else{
                        errorDataLiveData.postValue(new ErrorData("Something Went Wrong.."));
                    }
                }else{
                    errorDataLiveData.postValue(new ErrorData("Something Went Wrong"));
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                errorDataLiveData.postValue(new ErrorData(t.getMessage()));
            }
        });

    }

    private void parseJson(JsonObject jsonObject){
        //map json to object
        List<Article> articleList = new ArrayList<>();
        JsonArray jsonArray = jsonObject.getAsJsonArray("results");
        for (int i = 0; i < jsonArray.size() ; i++) {
            JsonObject object = (JsonObject) jsonArray.get(i);

            String articleTitle = object.get("title").getAsString();
            String articleUrl = object.get("url").getAsString();
            String publishedDate = object.get("published_date").getAsString();
            String author = object.get("byline").getAsString();

            JsonArray array = object.getAsJsonArray("media");
            JsonObject obj = array.get(0).getAsJsonObject();
            array = obj.getAsJsonArray("media-metadata");
            String url = array.get(0).getAsJsonObject().get("url").getAsString();

            Article article = new Article();
            article.setTitle(articleTitle);
            article.setPublishedDate(publishedDate);
            article.setCaptionUrl(url);
            article.setAuthor(author);
            article.setContentUrl(articleUrl);

            articleList.add(article);
        }
        this.articleMutableLiveData.postValue(articleList);
    }

    public MutableLiveData<List<Article>> getArticles() {
        return articleMutableLiveData;
    }

    public MutableLiveData<ErrorData> getErrorDataLiveData() {
        return errorDataLiveData;
    }
}
