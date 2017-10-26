package com.dailytracksolutions.android.daytracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by jacksonmalcolmchaplin on 17/10/17.
 */

public class EntryListFragment extends Fragment {

    private static  final String SAVED_SUBTITLE_VISIBILE = "subtitle";
    private RecyclerView mEntryRecylcerView;
    private EntryAdapter mAdapter;
    private boolean mSubtitleVisible;

    @Override
    public void onCreate(Bundle savedinstanceState) {
        super.onCreate(savedinstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entry_list, container, false);

        mEntryRecylcerView = (RecyclerView) view
                .findViewById(R.id.entry_recycler_view);
        mEntryRecylcerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null) {
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBILE);
        }

        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBILE, mSubtitleVisible);
    }
////////////////////////////////MENU ITEMS AND SUBTITLES///////////////////////////////////
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_entry_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);
        if (mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_entry:
                Entry entry = new Entry();
                EntryLab.get(getActivity()).addEntry(entry);
                Intent intent = EntryPagerActivity
                        .newIntent(getActivity(), entry.getId());
                startActivity(intent);
                return true;
//            case R.id.menu_settings:
//                Settings settings = new Settings();
//                SettingsLab.get(getActivity()).addSettings(settings);
//                Intent intent2 = SettingsPagerActivity
//                        .newIntent(getActivity(), settings.getSId());
//                startActivity(intent2);
//                return true;
            case R.id.show_subtitle:
            mSubtitleVisible = !mSubtitleVisible;
            getActivity().invalidateOptionsMenu();
            updateSubtitle();
            return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
        private void updateSubtitle() {
        EntryLab entryLab = EntryLab.get(getActivity());
        int entryCount = entryLab.getEntries().size();
        String subtitle = getString(R.string.subtitle_format, entryCount);

            if (!mSubtitleVisible) {
                subtitle = null;
            }

            AppCompatActivity activity =(AppCompatActivity) getActivity();
            activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private void updateUI() {
        EntryLab entryLab = EntryLab.get(getActivity());
        List<Entry> entries = entryLab.getEntries();

        if (mAdapter == null) {
            mAdapter = new EntryAdapter(entries);
            mEntryRecylcerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        updateSubtitle();
    }


    ////////////////////Entry HOLDER /////////////////////////////////////////////
    private class EntryHolder extends RecyclerView.ViewHolder
            implements  View.OnClickListener{

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Entry mEntry;
        private ImageView mWorkImageView;

        public EntryHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_entry, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.entry_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.entry_date);
            mWorkImageView = (ImageView) itemView.findViewById(R.id.work_checked);
        }
        public void bind(Entry entry) {
            mEntry = entry;
            mTitleTextView.setText(mEntry.getTitle());
            mDateTextView.setText(mEntry.getDate().toString());
            mWorkImageView.setVisibility(entry.isWork()? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View v) {
            Intent intent = EntryPagerActivity.newIntent(getActivity(), mEntry.getId());
            startActivity(intent);
        }
    }



    ////////////////////Entry ADAPTER /////////////////////////////////////////////

    private class EntryAdapter extends RecyclerView.Adapter<EntryHolder> {

        private List <Entry> mEntries;

        public EntryAdapter(List<Entry> entries) {
            mEntries = entries;
        }

        @Override
        public EntryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new EntryHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(EntryHolder holder, int position) {
            Entry entry = mEntries.get(position);
            holder.bind(entry);

        }

        @Override
        public int getItemCount() {
            return mEntries.size();
        }
    }

//        public void setEntry(List<Entry> entries) {
//            mEntry = entries;
//        }

}
