package com.dailytracksolutions.android.daytracker;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.dailytracksolutions.android.daytracker.database.EntryDbSchema.EntryDbSchema;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by jacksonmalcolmchaplin on 17/10/17.
 */

public class EntryFragment extends Fragment {


    private static final String ARG_ENTRY_ID = "entry_id";
    private static final String DIALOG_DATE = "DialogDate";

    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_PHOTO = 2;

    private Entry mEntry;
    private File mPhotoFile;
    private EditText mTitleField;
    private EditText mDetailsField;
    private Button mDateButton;
    private CheckBox mWorkCheckBox;
    private CheckBox mLeisureCheckBox;
    private CheckBox mSportCheckBox;
    private CheckBox mStudyCheckBox;
    private Button mSaveButton;
    private Button mDeleteButton;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;

    public static EntryFragment newInstance(UUID entryid) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ENTRY_ID, entryid);

        EntryFragment fragment = new EntryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID entryId = (UUID) getArguments().getSerializable(ARG_ENTRY_ID);
        mEntry = EntryLab.get(getActivity()).getEntry(entryId);
        mPhotoFile = EntryLab.get(getActivity()).getPhotoFile(mEntry);
    }

    @Override
    public void onPause() {
        super.onPause();

        EntryLab.get(getActivity())
                .updateEntry(mEntry);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_entry, container, false);

        mTitleField = (EditText) v.findViewById(R.id.entry_title);
        mTitleField.setText(mEntry.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                //lef tblank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mEntry.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //this one too

            }


        });

        mDetailsField = (EditText) v.findViewById(R.id.entry_details);
        mDetailsField.setText(mEntry.getDetails());
        mDetailsField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                //lef tblank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mEntry.setDetails(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //this one too

            }


        });

        mDateButton = (Button) v.findViewById(R.id.entry_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mEntry.getDate());
                dialog.setTargetFragment(EntryFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mWorkCheckBox = (CheckBox) v.findViewById(R.id.work_label);
        mWorkCheckBox.setChecked(mEntry.isWork());
        mWorkCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                mEntry.setWork(isChecked);

            }
        });

        mLeisureCheckBox = (CheckBox) v.findViewById(R.id.leisure_label);
        mLeisureCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                mEntry.setWork(isChecked);

            }
        });

        mSportCheckBox = (CheckBox) v.findViewById(R.id.sport_label);
        mSportCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                mEntry.setWork(isChecked);

            }
        });

        mStudyCheckBox = (CheckBox) v.findViewById(R.id.study_label);
        mStudyCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                mEntry.setWork(isChecked);

            }
        });

        mSaveButton = (Button) v.findViewById(R.id.button_save);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();

            }
        });


        mDeleteButton = (Button) v.findViewById(R.id.button_save);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();

            }
        });

//        mPhotoButton = (ImageButton) v.findViewById(R.id.entry_camera);
//        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        PackageManager packageManager = null;
//        boolean canTakePhoto = mPhotoFile != null &&
//                captureImage.resolveActivity(packageManager) != null;
//        mPhotoButton.setEnabled(canTakePhoto);
//
//        mPhotoButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Uri uri = FileProvider.getUriForFile(getActivity(),
//                        "com.dailytracksolutions.android.daytracker.fileprovider",
//                        mPhotoFile);
//                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//
//                List<ResolveInfo> cameraActivities = getActivity()
//                        .getPackageManager().queryIntentActivities(captureImage,
//                                PackageManager.MATCH_DEFAULT_ONLY);
//
//                for (ResolveInfo activity : cameraActivities) {
//                    getActivity().grantUriPermission(activity.activityInfo.packageName,
//                            uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//                }
//
//                startActivity(captureImage, REQUEST_PHOTO);
//            }
//        });
//
//        mPhotoView = (ImageView) v.findViewById(R.id.entry_photo);
//        updatePhotoView();



//        packageManager = getActivity().getPackageManager();
//        if (packageManager.resolveActivity(captureImage,
//                PackageManager.MATCH_DEFAULT_ONLY) == null) {
//            mPhotoButton.setEnabled(false);
//        }

        return v;
    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mEntry.setDate(date);
            updateDate();
//        } else if (requestCode == REQUEST_PHOTO){
//            Uri uri = FileProvider.getUriForFile(getActivity(),
//                    "com.dailytracksolutions.android.daytracker.fileprovider",
//                    mPhotoFile);
//
//            getActivity().revokeUriPermission(uri,
//                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//
//            updatePhotoView();
//
        }
    }

    private void updateDate() {
        mDateButton.setText(mEntry.getDate().toString());
    }

    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }
}