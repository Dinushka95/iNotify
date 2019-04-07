package com.example.inotify.helpers;

import android.content.Context;

import com.example.inotify.configs.TbNames;
import com.example.inotify.dbHelpers.ApplicationDbHelper;
import com.example.inotify.dbHelpers.CommonAppCountDbHelper;

public class CommonAppCountHelper {
    private Context c1;

    public CommonAppCountHelper(Context context) {
        this.c1 = context;
    }

    public boolean characteristicsInsert()
    {
        return CommonAppCountDbHelper.getInstance(c1).characteristicsInsert();
    }

    public int commonCommunicationAppAvg()
    {
        return CommonAppCountDbHelper.getInstance(c1).commonCommunicationAppAvg();
    }
    public int commonGamingAppAvg()
    {
        return CommonAppCountDbHelper.getInstance(c1).commonGamingAppAvg();
    }
    public int commonMusicVideoAppAvg()
    {
        return CommonAppCountDbHelper.getInstance(c1).commonMusicVideoAppAvg();
    }
    public int commonPersonalizationAppAvg()
    {
        return CommonAppCountDbHelper.getInstance(c1).commonPersonalizationAppAvg();
    }
    public int commonPhotograpyAppAvg()
    {
        return CommonAppCountDbHelper.getInstance(c1).commonPhotograpyAppAvg();
    }

    public int commonSocialAppAvg()
    {
        return CommonAppCountDbHelper.getInstance(c1).commonSocialAppAvg();
    }

    public void  characteristicsInsertOnAvailability()
    {
        if(! CommonAppCountDbHelper.getInstance(c1).cheackAvailability(TbNames.COMMONAPPCOUNT_TABLE))
        {
            this.characteristicsInsert();
        }

    }

}
