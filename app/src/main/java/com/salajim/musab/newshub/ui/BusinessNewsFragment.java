package com.salajim.musab.newshub.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salajim.musab.newshub.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessNewsFragment extends Fragment {


    public BusinessNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_business_news, container, false);
    }

}
