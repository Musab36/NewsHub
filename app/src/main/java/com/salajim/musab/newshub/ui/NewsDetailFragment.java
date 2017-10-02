package com.salajim.musab.newshub.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.salajim.musab.newshub.R;
import com.salajim.musab.newshub.models.News;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


public class NewsDetailFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.newsImageView) ImageView mNewsImageView;
    @Bind(R.id.newsDescriptionTextView) TextView mNewsDescriptionTextView;
    @Bind(R.id.publishedAtTextView) TextView mPublishedAtTextView;
    @Bind(R.id.readMoreTextView) TextView mReadMoreTextView;

    private News mNews;


    // newInstance(), is used instead of a constructor and returns a new instance of NewsDetailFragment
    public static NewsDetailFragment newInstance(News news) {
        NewsDetailFragment newsDetailFragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("news", Parcels.wrap(news));
        newsDetailFragment.setArguments(args);
        return newsDetailFragment;
    }

    //onCreate(), is called when the fragment is created. Here, we unwrap our news
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNews = Parcels.unwrap(getArguments().getParcelable("news"));
    }

    // onCreateView(), this news object is then used to set our ImageView and TextViews
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);
        ButterKnife.bind(this, view);

        //Picasso handling the display of images
        Picasso.with(view.getContext())
                .load(mNews.getUrlToImage())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mNewsImageView);

        mNewsDescriptionTextView.setText(mNews.getDescription());
        mPublishedAtTextView.setText("Published At: " + mNews.getPublishedAt());

        mReadMoreTextView.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == mReadMoreTextView) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mNews.getUrl()));
            startActivity(webIntent);
        }
    }

}
