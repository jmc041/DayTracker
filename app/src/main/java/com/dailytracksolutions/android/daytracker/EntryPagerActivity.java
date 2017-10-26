package com.dailytracksolutions.android.daytracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

/**
 * Created by jacksonmalcolmchaplin on 18/10/17.
 */

public class EntryPagerActivity extends AppCompatActivity {
    private static final String EXTRA_ENTRY_ID =
            "com.dailytracksolutions.android.daytracker.entry.id";

    private ViewPager mViewPager;
    private List<Entry> mEntries;

    public static Intent newIntent(Context packageContext, UUID entryId) {
        Intent intent = new Intent(packageContext, EntryPagerActivity.class);
        intent.putExtra(EXTRA_ENTRY_ID, entryId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_pager);

        UUID entryId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_ENTRY_ID);

        mViewPager = (ViewPager) findViewById(R.id.entry_view_pager);

        mEntries = EntryLab.get(this).getEntries();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Entry entry = mEntries.get(position);
                return EntryFragment.newInstance(entry.getId());
            }

            @Override
            public int getCount() {
                return mEntries.size();
            }
        });
        //View loads the "page"/entry that is clicked instead of opening the first entry/activity
        for (int i = 0 ; i < mEntries.size(); i++) {
            if (mEntries.get(i).getId().equals(entryId)) {
                mViewPager.setCurrentItem(i);
            }
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
   
}
