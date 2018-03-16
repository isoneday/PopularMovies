package com.blackswan.popularmovies.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackswan.popularmovies.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TesDetailFragment extends Fragment {


    public TesDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tes_detail, container, false);
    }

}
