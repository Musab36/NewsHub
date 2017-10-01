package com.salajim.musab.newshub;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private ArrayList<News> mNewses = new ArrayList<>();
    private Context mContext;

    public NewsListAdapter(Context context, ArrayList<News> newses) {
        mContext = context;
        mNewses = newses;
    }

    // onCreateViewHolder method which inflates the layout, and creates the ViewHolder object required from the adapter
    @Override
    public NewsListAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, parent, false);
        NewsViewHolder viewHolder = new NewsViewHolder(view);
        return viewHolder;
    }

    // onBindViewHolder method which updates the contents of the ItemView to reflect the news in the given position
    @Override
    public void onBindViewHolder(NewsListAdapter.NewsViewHolder holder, int position) {
        holder.bindNews(mNewses.get(position));
    }

    // getItemCount method which sets the number of items the adapter will display
    @Override
    public int getItemCount() {
        return mNewses.size();
    }

    // viewHolder class as an inner/nested class
    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.newsImageView) ImageView mNewsImageView;
        @Bind(R.id.newsTitleTextView) TextView mNewsTitleTextView;
        @Bind(R.id.authorTextView) TextView mAuthorTextView;

        private Context mContext;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, NewsDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("newses", Parcels.wrap(mNewses));
            mContext.startActivity(intent);
        }

        public void bindNews(News news) {
            Picasso.with(mContext)
                    .load(news.getUrlToImage())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mNewsImageView);

            mNewsTitleTextView.setText(news.getTitle());
            mAuthorTextView.setText(news.getAuthor());
        }
    }
}
