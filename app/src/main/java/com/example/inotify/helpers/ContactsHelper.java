package com.example.inotify.helpers;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.inotify.configs.TbNames;
import com.example.inotify.dbHelpers.ApplicationDbHelper;
import com.example.inotify.dbHelpers.ContactsDbHelper;
import com.example.inotify.dbHelpers.UserCharacteristics_DbHelper;
import com.example.inotify.models.ContactsModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContactsHelper {
    private Context c1;

    public ContactsHelper(Context context) {
        this.c1 = context;
    }

    public int getContacts(Context context) {

        ContentResolver cr = context.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        List<ContactsModel> myList = new ArrayList<>();

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);

                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        myList.add(new ContactsModel(name, phoneNo));

                        Collections.sort(myList, new Comparator<ContactsModel>() {
                            @Override
                            public int compare(ContactsModel o1, ContactsModel o2) {
                                return o1.getName().compareTo(o2.getName());
                            }
                        });

                    }
                    pCur.close();
                }

            }
        }

        if (cur != null) {
            cur.close();
        }

        int count = myList.size();
        Log.d("inotify","contacts..........." + count);

        UserCharacteristics_DbHelper UserCharacteristics_DbHelper = new UserCharacteristics_DbHelper(context);
        UserCharacteristics_DbHelper.contactCount_insert(String.valueOf(count));
        UserCharacteristics_DbHelper.close();

        return count;

    }


    public int getcontactToday()
    {

        return  ContactsDbHelper.getInstance (c1).ContactsTodayGet();

    }

//    public void getContactAVG()
//    {
//        ContactsDbHelper contactsDbHelper = new ContactsDbHelper(c1);
//        contactsDbHelper.ContactsAvgGet();
//        Log.d("inotify","contact AVG----" + contactsDbHelper.ContactsAvgGet());
//
//    }


    public boolean ContactsCountInsert()
    {
        return  ContactsDbHelper.getInstance (c1).ContactsCountInsert();
    }

    public void  ContactsCountInsertOnAvailability()
    {
        if(! ContactsDbHelper.getInstance(c1).cheackAvailability(TbNames.CONTACTCOUNT_TABLE))
        {
            this.ContactsCountInsert();
        }

    }

}
