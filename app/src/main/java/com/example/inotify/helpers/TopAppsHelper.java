package com.example.inotify.helpers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.inotify.dbHelpers.TopAppDbHelper;
import com.example.inotify.interfaces.MyCallback;
import com.example.inotify.models.ApplicationInfoModel;
import com.example.inotify.models.TopAppModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TopAppsHelper {

    private Context c1;
    public TopAppsHelper(Context context) {
        c1=context;
    }
//No of social media apps -- If available top apps or not
    //No of gaming apps -- If available top apps or not
    //No of music and video apps -- If available top apps or not
    //No of chatting apps -- If available top apps or not
    //check with pre define top apps


    public List<ApplicationInfoModel> topAppSocial()
{
    TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
    return topAppDbHelper.topAppSocilGet();
}


    public List<ApplicationInfoModel> topAppPhotograpy()
    {
        TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
        return topAppDbHelper.topAppPhotograpyGet();
    }

    public List<ApplicationInfoModel> topAppPersonalization()
    {
        TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
        return topAppDbHelper.topAppPersonalizationGet();
    }


    public List<ApplicationInfoModel> topAppCommunication()
    {
        TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
        return topAppDbHelper.topAppCommunicationGet();
    }

    public List<ApplicationInfoModel> topAppGaming()
    {
        TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
        return topAppDbHelper.topAppGamingGet();
    }

    public List<ApplicationInfoModel> topAppBusiness()
    {
        TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
        return topAppDbHelper.topAppBusinessGet();
    }

    public List<ApplicationInfoModel> topAppDating()
    {
        TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
        return topAppDbHelper.topAppDatingGet();
    }

    public List<ApplicationInfoModel> topAppEntertainment()
    {
        TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
        return topAppDbHelper.topAppEntertainmentGet();
    }

    public List<ApplicationInfoModel> topAppProductivity()
    {
        TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
        return topAppDbHelper.topAppProductivityGet();
    }

    public List<ApplicationInfoModel> topAppTools()
    {
        TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
        return topAppDbHelper.topAppToolGet();
    }

    public List<ApplicationInfoModel> topAppMusicVideo()
    {
        TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
        return topAppDbHelper.topAppMusicVideoGet();
    }

    public int appCountGet()
    {

        TopAppDbHelper topAppDbHelper = new TopAppDbHelper(c1);
        return (int) topAppDbHelper.SocialAppCountGet();
    }


    public  List<TopAppModel> syncTopApps(){

/*        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("topApplications")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("inotify", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("inotify", "Error getting documents.", task.getException());
                        }
                    }
                });*/


        final List<TopAppModel> topAppModelList = new ArrayList<>();




        return topAppModelList;
    }

    public void readData(MyCallback myCallback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("topApplications")
                .whereEqualTo("date", "03202019")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        List<TopAppModel> topAppModelList = new ArrayList<>();

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                TopAppModel topAppModel = new TopAppModel();
                                topAppModel = document.toObject(TopAppModel.class);


                                topAppModelList.add(topAppModel);
                                //Log.d("inotify", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d("inotify", "Error getting documents: ", task.getException());
                        }
                        myCallback.onCallback(topAppModelList);
                    }
                });
        //Log.d("inotify", "Error getting documents: "+topAppModelList.size());

    }
}
