package com.example.inotify.helpers;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;

import java.util.HashSet;

public class CalenderHelper {
    public String CalenderUsage()
    {
//        ContentResolver contentResolver = mContext.getContentResolver();
//        final Cursor cursor = contentResolver.query(Uri.parse("content://com.android.calendar/calendars"),
//                (new String[] { "_id", "displayName", "selected" }), null, null, null);
//
//        HashSet<String> calendarIds = new HashSet<String>();
//        while (cursor.moveToNext()) {
//            final String _id = cursor.getString(0);
//            final String displayName = cursor.getString(1);
//            final Boolean selected = !cursor.getString(2).equals("0");
//            //System.out.println("Id: " + _id + " Display Name: " + displayName + " Selected: " + selected);
//            calendarIds.add(_id);
//
//        }
//        Uri.Builder eventsUriBuilder = CalendarContract.Instances.CONTENT_URI
//                .buildUpon();
//        ContentUris.appendId(eventsUriBuilder, timeNow);
//        ContentUris.appendId(eventsUriBuilder, endOfToday);
//        Uri eventsUri = eventsUriBuilder.build();
//        Cursor cursor = null;
//        cursor = mContext.getContentResolver().query(eventsUri, columns, null, null, CalendarContract.Instances.DTSTART + " ASC");
       return  null;
   }
}
