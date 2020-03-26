package com.goodwill.apainterviewapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.goodwill.apainterviewapp.ui.ArticleDetailsActivity;
import com.goodwill.apainterviewapp.R;
import com.goodwill.apainterviewapp.databinding.ArticleLayoutBinding;
import com.goodwill.apainterviewapp.models.Article;

import java.util.List;

/**
 * Adapter to load articles
 */
public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder> {

    private List<Article> articles;

    public ArticlesAdapter(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ArticleLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.article_layout, parent, false);
        return new ArticlesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.bind(article);
        holder.articleLayoutBinding.getRoot().setOnClickListener(view-> holder.loadDetails(article));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ArticlesViewHolder extends RecyclerView.ViewHolder {
        ArticleLayoutBinding articleLayoutBinding;
        public ArticlesViewHolder(ArticleLayoutBinding articleLayoutBinding) {
            super(articleLayoutBinding.getRoot());
            this.articleLayoutBinding = articleLayoutBinding;
        }

        public void bind(Article article){
            this.articleLayoutBinding.setArticle(article);
            this.articleLayoutBinding.executePendingBindings();
        }

        private void loadDetails(Article article){
            Context context =  this.articleLayoutBinding.getRoot().getContext();

            context.startActivity(ArticleDetailsActivity.newInstance(context,article));
        }
    }
}
