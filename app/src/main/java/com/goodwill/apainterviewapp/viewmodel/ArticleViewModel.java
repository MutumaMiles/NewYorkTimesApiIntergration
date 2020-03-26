package com.goodwill.apainterviewapp.viewmodel;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.goodwill.apainterviewapp.models.Article;
import com.goodwill.apainterviewapp.models.ErrorData;
import com.goodwill.apainterviewapp.repository.ArticleRepository;

import java.util.List;

public class ArticleViewModel extends ViewModel {
    private MutableLiveData<ErrorData> errorDataLiveData;
    private MutableLiveData<List<Article>> articleMutableLiveData;
    private ArticleRepository repository;

    public void init(){
        repository = ArticleRepository.getInstance();
        articleMutableLiveData = new MutableLiveData<>();
    }

    public void loadArticles(Context context){
        repository.loadArticles();
        repository.getArticles().observe((LifecycleOwner) context, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                if(articles==null)
                    return;

                articleMutableLiveData.postValue(articles);
            }
        });

        errorDataLiveData = repository.getErrorDataLiveData();
    }

    public MutableLiveData<ErrorData> getErrorDataLiveData() {
        return errorDataLiveData;
    }

    public MutableLiveData<List<Article>> getArticleMutableLiveData() {
        return articleMutableLiveData;
    }
}
