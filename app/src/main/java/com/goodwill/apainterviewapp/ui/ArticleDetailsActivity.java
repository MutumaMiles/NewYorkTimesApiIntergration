package com.goodwill.apainterviewapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.goodwill.apainterviewapp.R;
import com.goodwill.apainterviewapp.databinding.ActivityArticleDetailsBinding;
import com.goodwill.apainterviewapp.models.Article;

public class ArticleDetailsActivity extends AppCompatActivity {

    private static final String ARTICLE_DETAILS = "ARTICLES";

    public static Intent newInstance(Context context, Article article) {
        Intent intent = new Intent(context, ArticleDetailsActivity.class);
        intent.putExtra(ARTICLE_DETAILS, article);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        ActivityArticleDetailsBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_article_details);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.read_article);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Article article = (Article) getIntent().getSerializableExtra(ARTICLE_DETAILS);
        WebView webView = binding.webView;
        webView.loadUrl(article.getContentUrl());
    }
}
