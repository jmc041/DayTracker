package com.dailytracksolutions.android.daytracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import static android.R.attr.fragment;

/**
 * Created by jacksonmalcolmchaplin on 26/10/17.
 */

public class SettingsActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new SettingsFragment();
    }
}
