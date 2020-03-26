package com.goodwill.apainterviewapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.goodwill.apainterviewapp.R;
import com.goodwill.apainterviewapp.adapters.ArticlesAdapter;
import com.goodwill.apainterviewapp.databinding.ActivityMainBinding;
import com.goodwill.apainterviewapp.models.Article;
import com.goodwill.apainterviewapp.viewmodel.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;

public class ArticlesListActivity extends AppCompatActivity {
    /**
     * instant variables and views
     */

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private  ArticleViewModel articleViewModel;
    private List<Article> articles;
    private ArticlesAdapter adapter;
    private LinearLayout errorLayout;
    private Button retryBtn;
    private TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        progressBar = binding.progressBar;

        //initialize RecyclerView
        recyclerView = binding.articlesRecylerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        articles = new ArrayList<>();
        adapter = new ArticlesAdapter(articles);
        recyclerView.setAdapter(adapter);

        errorLayout = binding.errorLayout;
        errorTextView = binding.errorTxt;
        retryBtn = binding.retryBtn;

        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
        articleViewModel.init();
        loadArticles();

    }

    /**
     *
     */
    private void displayArticles(){
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        errorLayout.setVisibility(View.GONE);
    }

    private void loadArticles(){
        articleViewModel.loadArticles(this);
        articleViewModel.getArticleMutableLiveData().observe(this,articlesList -> {
            this.articles.addAll(articlesList);
            adapter.notifyDataSetChanged();
            displayArticles();
        });

        articleViewModel.getErrorDataLiveData().observe(this,errorData -> showError(errorData.getErrorName()));
    }

    /**
     *
     * @param error
     */
    private void showError(String error){
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        errorLayout.setVisibility(View.VISIBLE);

        errorTextView.setText(error);
        retryBtn.setOnClickListener(view->{
            loadArticles();
            showLoadingBar();
        });
    }

    /**
     *
     */
    private void showLoadingBar(){
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        errorLayout.setVisibility(View.GONE);
    }
}
