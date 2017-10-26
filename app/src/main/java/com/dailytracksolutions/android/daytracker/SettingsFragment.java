package com.dailytracksolutions.android.daytracker;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

/**
 * Created by jacksonmalcolmchaplin on 20/10/17.
 */

public class SettingsFragment extends Fragment {
    private Settings mSettings;
    private EditText mNameField; //id is settings_name_title
    private EditText mCommentsField; //id is settings_comments_details
    private EditText mEmailField; // id is settings_email
    private RadioGroup mGenderGroup; // id is settings_gender
    private RadioButton mRadioGender;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSettings = new Settings();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        mNameField = (EditText) v.findViewById(R.id.settings_name_title);
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // left blank on purpose
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mSettings.setName(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                //left blank
            }
        });

        mCommentsField = (EditText) v.findViewById(R.id.settings_comments_details);
        mCommentsField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // left blank on purpose
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mSettings.setComments(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                //left blank
            }
        });

        mEmailField = (EditText) v.findViewById(R.id.settings_email);
        mEmailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // left blank on purpose
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mSettings.setEmail(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                //left blank
            }
        });


        mGenderGroup = (RadioGroup) v.findViewById(R.id.settings_gender);
        mGenderGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    //which button was selected
                if(checkedId == R.id.radioButton_male) {
                    Toast.makeText(getActivity(), "Chosen Gender: Male"
                            , Toast.LENGTH_SHORT).show();
                } else  {
                    Toast.makeText(getActivity(),"Chosen Gender: Female"
                    ,Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }
//
//        return  v;
//    }
}
