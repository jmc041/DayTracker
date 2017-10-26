package com.dailytracksolutions.android.daytracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.UUID;

/**
 * Created by jacksonmalcolmchaplin on 19/10/17.
 */

public class SettingsListActivity extends SingleFragmentActivity {

//    private static Intent intent;

    @Override
    protected Fragment createFragment() {
        return new SettingsFragment();
    }

//    public static Intent newIntent(FragmentActivity fragmentActivity, UUID settingsId) {
//
//        return intent;
//    }

//    public static Intent newIntent(Context packageContext, UUID settingsId) {
//        Intent intent = new Intent(packageContext, EntryPagerActivity.class);
//        intent.putExtra(EXTRA_ENTRY_ID, settingsId);
//        return intent;
//    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_settings);
//
//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
//
//        if (fragment ==null) {
//            fragment = new SettingsFragment();
//            fm.beginTransaction()
//                    .add(R.id.fragment_container, fragment)
//                    .commit();
//        }
//    }
}
