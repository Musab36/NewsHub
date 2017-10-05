package com.salajim.musab.newshub.ui;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.salajim.musab.newshub.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsCategoryActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.latestNewsBtn) Button mLatestNewsBtn;
    @Bind(R.id.generalNewsBtn) Button mGeneralNewsBtn;
    @Bind(R.id.techNewsBtn) Button mTechNews;
    @Bind(R.id.businessNewsBtn) Button mBusinessNewsBtn;
    @Bind(R.id.entertainmentNewsBtn) Button mEnterTainmentNewsBtn;
    @Bind(R.id.nationalGeographic) Button mNationalGeographic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_category);
        ButterKnife.bind(this);

        //We attach click listeners to the buttons
        mLatestNewsBtn.setOnClickListener(this);
        mGeneralNewsBtn.setOnClickListener(this);
        mTechNews.setOnClickListener(this);
        mBusinessNewsBtn.setOnClickListener(this);
        mEnterTainmentNewsBtn.setOnClickListener(this);
        mNationalGeographic.setOnClickListener(this);

        //Background Image Animations
        final ImageView backgroundOne = (ImageView) findViewById(R.id.background_one);
        final ImageView backgroundTwo = (ImageView) findViewById(R.id.background_two);

        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(10000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundOne.getWidth();
                final float translationX = width * progress;
                backgroundOne.setTranslationX(translationX);
                backgroundTwo.setTranslationX(translationX - width);
            }
        });
        animator.start();
    }

    //Intents
    @Override
    public void onClick(View v) {
        if(v == mLatestNewsBtn) {
            Intent intent = new Intent(NewsCategoryActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if(v == mGeneralNewsBtn) {
            Intent intent = new Intent(NewsCategoryActivity.this, GeneralNewsActivity.class);
            startActivity(intent);
        }
        if(v == mTechNews) {
            Intent intent = new Intent(NewsCategoryActivity.this, TechnologyNewsActivity.class);
            startActivity(intent);
        }
        if(v == mBusinessNewsBtn) {
            Intent intent = new Intent(NewsCategoryActivity.this, BusinessNewsActivity.class);
            startActivity(intent);
        }
        if(v == mEnterTainmentNewsBtn) {
            Intent intent = new Intent(NewsCategoryActivity.this, EntertainmentNewsActivity.class);
            startActivity(intent);
        }
        if(v == mNationalGeographic) {
            Intent intent = new Intent(NewsCategoryActivity.this, NationalGeographicActivity.class);
            startActivity(intent);
        }
    }
}
