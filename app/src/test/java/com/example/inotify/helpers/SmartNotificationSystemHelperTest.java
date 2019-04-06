package com.example.inotify.helpers;

import android.content.Context;

import com.example.inotify.dbHelpers.ApplicationDbHelper;
import com.example.inotify.models.SNSModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SmartNotificationSystemHelperTest {

    @Mock
    Context mMockContext;

    @Test
    public void convertSNSdataToNumberic() {


        String appName = ApplicationDbHelper.getInstance(mMockContext).getApplicationDetailsById("0").getAppName();
        String appid = ApplicationDbHelper.getInstance(mMockContext).getApplicationDetailsByPackName(appName).getAppName();


        List<SNSModel> snsModelListData = new ArrayList<SNSModel>();
        snsModelListData.add(new SNSModel("Monday","2020","0.5","0.5","social","business",appName));
        snsModelListData.add(new SNSModel("Tuesday","2020","0.5","0.5","professional","communication",appName,"5"));
        snsModelListData.add(new SNSModel("Saturday","2020","0.5","0.5","social","famailymusicvideo",appName));
        snsModelListData.add(new SNSModel("Sunday","2020","0.5","0.5","professional","gaming",appName,"2"));

        List<SNSModel> snsModelListNumeric = new ArrayList<SNSModel>();
        snsModelListNumeric.add(new SNSModel("1","2020","0.5","0.5","1","1",appid));
        snsModelListNumeric.add(new SNSModel("2","2020","0.5","0.5","2","2",appid,"5"));
        snsModelListNumeric.add(new SNSModel("6","2020","0.5","0.5","1","6",appid));
        snsModelListNumeric.add(new SNSModel("7","2020","0.5","0.5","2","7",appid,"2"));



    }

    @Test
    public void convertNumbericToSNSdata() {
    }

}