package com.dailytracksolutions.android.daytracker;

import android.support.v4.app.Fragment;

/**
 * Created by jacksonmalcolmchaplin on 17/10/17.
 */

public class EntryListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new EntryListFragment();
    }
}
