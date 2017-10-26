package com.dailytracksolutions.android.daytracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jacksonmalcolmchaplin on 21/10/17.
 */

public class SettingsListFragment extends Fragment {

    //nothing yet

    private RecyclerView mSettingsRecylcerView;
    private RecyclerView mSettingsRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings_list, container, false);

        mSettingsRecylcerView = (RecyclerView) view
                .findViewById(R.id.settings_recycler_view);
        mSettingsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        //updateUI();
        return view;
    }
}
