package com.salajim.musab.newshub.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.salajim.musab.newshub.models.News;
import com.salajim.musab.newshub.ui.NewsDetailFragment;

import java.util.ArrayList;

public class NewsPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<News> mNewses;

    //A constructor where we set the required FragmentManager and array list of news we will be swiping through.
    public NewsPagerAdapter(FragmentManager fm, ArrayList<News> newses) {
        super(fm);
        mNewses = newses;
    }

    //Returns an instance of the NewsDetailFragment for the news in the position provided as an argument
    @Override
    public Fragment getItem(int position) {
        return NewsDetailFragment.newInstance(mNewses.get(position));
    }

    //Determines how many news are in our Array List. This lets our adapter know how many fragments it must create.
    @Override
    public int getCount() {
        return mNewses.size();
    }
}
