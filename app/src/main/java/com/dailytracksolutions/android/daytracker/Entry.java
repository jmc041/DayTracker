package com.dailytracksolutions.android.daytracker;

import java.util.Date;
import java.util.UUID;

/**
 * Created by jacksonmalcolmchaplin on 17/10/17.
 */

public class Entry {

    private UUID mId;
    private String mTitle;
    private String mDetails;
    private Date mDate;
    private boolean mWork;
    private boolean mLeisure;
    private boolean mSport;
    private boolean mStudy;

    public Entry() {
        this(UUID.randomUUID());
//        mId = UUID.randomUUID();
//        mDate = new Date();
    }

    public Entry(UUID id) {
        mId = id;
        mDate = new Date();

    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDetails() {
        return mDetails;
    }

    public void setDetails(String details) {
        mDetails = details;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isWork() {
        return mWork;
    }

    public void setWork(boolean work) {
        mWork = work;
    }

    public boolean isLeisure() {
        return mLeisure;
    }

    public void setLeisure(boolean leisure) {
        mLeisure = leisure;
    }

    public boolean isSport() {
        return mSport;
    }

    public void setSport(boolean sport) {
        mSport = sport;
    }

    public boolean isStudy() {
        return mStudy;
    }

    public void setStudy(boolean study) {
        mStudy = study;
    }

    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }
}
