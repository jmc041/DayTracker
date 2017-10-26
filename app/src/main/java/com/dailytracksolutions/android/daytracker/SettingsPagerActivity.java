package com.dailytracksolutions.android.daytracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by jacksonmalcolmchaplin on 21/10/17.
 */

public class SettingsPagerActivity extends AppCompatActivity{
    private static final String EXTRA_SETTINGS_ID =
            "com.dailytracksolutions.android.daytracker.settings.id";

    private ViewPager mViewPager;
    private List<Settings> mSettings;

    public static Intent newIntent(Context packageContext, UUID settingsId) {
        Intent intent2 = new Intent(packageContext, EntryPagerActivity.class);
        intent2.putExtra(EXTRA_SETTINGS_ID, settingsId);
        return intent2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_pager);

        UUID settingsId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_SETTINGS_ID);

        mViewPager = (ViewPager) findViewById(R.id.entry_view_pager);

        mSettings = SettingsLab.get(this).getSettings();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Settings settings = mSettings.get(position);
                return EntryFragment.newInstance(settings.getSId());
            }

            @Override
            public int getCount() {
                return mSettings.size();
            }
        });
        //View loads the "page"/entry that is clicked instead of opening the first entry/activity
        for (int i = 0 ; i < mSettings.size(); i++) {
            if (mSettings.get(i).getSId().equals(settingsId)) {
                mViewPager.setCurrentItem(i);
            }
        }

}}


