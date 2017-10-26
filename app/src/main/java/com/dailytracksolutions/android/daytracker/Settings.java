package com.dailytracksolutions.android.daytracker;

import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.widget.RadioGroup;

import java.util.UUID;

/**
 * Created by jacksonmalcolmchaplin on 19/10/17.
 */

public class Settings {
    private UUID mSId;
    private String mName;
    private String mComments;
    private String mEmail;


    public Settings() {
        mSId = UUID.randomUUID();

    }

    public UUID getSId() {
        return mSId;
    }

//    public UUID getId() {
//        return mSId;
//    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getComments() {
        return mComments;
    }

    public void setComments(String comments) {
        mComments = comments;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }
}
