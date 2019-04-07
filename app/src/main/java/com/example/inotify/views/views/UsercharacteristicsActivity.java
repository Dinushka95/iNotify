package com.example.inotify.views.views;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.example.inotify.R;
import com.example.inotify.dbHelpers.ApplicationDbHelper;
import com.example.inotify.dbHelpers.CharacteristicFinalDbHelper;
import com.example.inotify.dbHelpers.ContactsDbHelper;
import com.example.inotify.helpers.AppUsageHelper;
import com.example.inotify.helpers.ApplicationsHelper;
import com.example.inotify.helpers.AttributeCountHelper;
import com.example.inotify.helpers.CalenderEventHelper;
import com.example.inotify.helpers.CallUsageHelper;
import com.example.inotify.helpers.ChargerHelper;
import com.example.inotify.helpers.ContactsHelper;
import com.example.inotify.helpers.ScreenOnTimeHelper;
import com.example.inotify.models.ApplicationInfoModel;
import com.example.inotify.models.CharacteristicsFinalModel;

import java.util.ArrayList;
import java.util.List;

public class UsercharacteristicsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_characteristics);

    }

    public void DisplayOpenness(View view) {
       // this.displayOpenness();


    }

    public void test1(View view) {

    }

    public void test2(View view) {
//
//       CharacteristicFinalDbHelper characteristicFinalDbHelper = new CharacteristicFinalDbHelper(this);
//        List<CharacteristicsFinalModel> ListCharacteristicsFinalModel = new ArrayList<>();
//        ListCharacteristicsFinalModel = characteristicFinalDbHelper.CharacteristicsGet();
//        Log.d("inotify","ListCharacteristicsFinalModel    "+ListCharacteristicsFinalModel);

//        ApplicationDbHelper applicationDbHelper = new ApplicationDbHelper(this);
//        List<ApplicationInfoModel> mySocialApp = new ArrayList<>();
//        mySocialApp =  applicationDbHelper.mySocialAppAllGet();
//        Log.d("inotify","mySocialApp    "+mySocialApp);

        Intent intent = new Intent(this,ChartCharacteristicsActivity.class);
        startActivity(intent);

     }


    public void Details(View view) {
        Intent intent = new Intent(this,Conscientiousness.class);
        startActivity(intent);


    }


}
