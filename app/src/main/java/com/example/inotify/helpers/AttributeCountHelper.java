package com.example.inotify.helpers;

import android.content.Context;

import com.example.inotify.dbHelpers.AttributeCountDbHelper;

public class AttributeCountHelper {
    private Context c1;

    public AttributeCountHelper(Context context) {
        this.c1 = context;
    }

    public long ScreenOnTimeCountAvgGet()
    {
        AttributeCountDbHelper attributeCountDbHelper = new AttributeCountDbHelper(c1);
        return attributeCountDbHelper.ScreenOnTimeCountAvgGet();
    }

    public long ContactsAvgGet()
    {
        AttributeCountDbHelper attributeCountDbHelper = new AttributeCountDbHelper(c1);
        return attributeCountDbHelper.ContactsAvgGet();
    }

    public long chargingCountAvgGet()
    {
        AttributeCountDbHelper attributeCountDbHelper = new AttributeCountDbHelper(c1);
        return attributeCountDbHelper.chargingCountAvgGet();
    }

    public long appCountAvgGet()
    {
        AttributeCountDbHelper attributeCountDbHelper = new AttributeCountDbHelper(c1);
        return attributeCountDbHelper.appCountAvgGet();
    }


}
