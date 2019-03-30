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
        return AttributeCountDbHelper.getInstance(c1).ScreenOnTimeCountAvgGet();
    }

    public long ContactsAvgGet()
    {
        return AttributeCountDbHelper.getInstance(c1).ContactsAvgGet();
    }

    public long chargingCountAvgGet()
    {
        return AttributeCountDbHelper.getInstance(c1).chargingCountAvgGet();
    }

    public long appCountAvgGet()
    {
        return AttributeCountDbHelper.getInstance(c1).appCountAvgGet();
    }


}
