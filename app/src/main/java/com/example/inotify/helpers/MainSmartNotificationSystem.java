package com.example.inotify.helpers;

import android.content.Context;
import android.util.Log;

import com.example.inotify.dbHelpers.SN_DbHelper;
import com.example.inotify.models.SNSModel;
import com.example.inotify.views.views.MainStartPermissionActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainSmartNotificationSystem {

    SN_DbHelper SN_DbHelper;
    SNSModel predict_snsModel;

    public MainSmartNotificationSystem(Context context, SNSModel _snsModel) {
        SN_DbHelper = new SN_DbHelper(context);
        predict_snsModel = _snsModel;
    }

    public String getPrediction() {

        String cc = "";
        try {
            cc = HttpPost("https://us-central1-inotify23.cloudfunctions.net/hello_http");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("inotify", "Main-MainSmartNotificationSystem--Predicted output from ML model-Raw value---" + cc);

        return cc;
    }

    private String HttpPost(String myUrl) throws IOException, JSONException {
        String result = "";

        URL url = new URL(myUrl);

        // 1. create HttpURLConnection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

        // 2. build JSON object
        JSONObject jsonObject = buidJsonObject();

        // 3. add JSON content to POST request body
        result = setPostRequestContent(conn, jsonObject);

        // 4. make POST request to the given URL
        conn.connect();

        // 5. return response message
        conn.getResponseMessage();


        return result;

    }

    private JSONObject buidJsonObject() {

        ArrayList<SNSModel> _snsModels = new ArrayList<>();

        JSONArray jsonArray1 = new JSONArray();
        //all data
        _snsModels = SN_DbHelper.getALL();

        int count = _snsModels.size();
        Log.d("inotify", "Main-MainSmartNotificationSystem--SNS model data from DB row count---" + count);
        for (SNSModel x : _snsModels) {
            JSONObject jsonData1 = new JSONObject();

            if (x.getVtime() == null || x.getVtime() == "") {

            } else {
                try {
                    // Log.v("inotify","AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa"+x.getDay().toString());
                    jsonData1.accumulate("sns_day", x.getDay());
                    jsonData1.accumulate("sns_time", x.getTime());
                    jsonData1.accumulate("sns_busyornot", x.getBusyornot());
                    jsonData1.accumulate("sns_attentiviness", x.getAttentiviness());
                    jsonData1.accumulate("sns_userchaacteristics", x.getUserchaacteristics());
                    jsonData1.accumulate("sns_notificationtype", x.getNotificationtype());
                    jsonData1.accumulate("sns_appname", x.getAppname());
                    jsonData1.accumulate("sns_vtime", x.getVtime());

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                jsonArray1.put(jsonData1);
            }
        }

        JSONObject jsonPredict = new JSONObject();

        try {

            jsonPredict.accumulate("sns_day", Integer.valueOf(predict_snsModel.getDay()));
            jsonPredict.accumulate("sns_time", Integer.valueOf(predict_snsModel.getTime()));
            jsonPredict.accumulate("sns_busyornot", Integer.valueOf(predict_snsModel.getBusyornot()));
            jsonPredict.accumulate("sns_attentiviness", Integer.valueOf(predict_snsModel.getAttentiviness()));
            jsonPredict.accumulate("sns_userchaacteristics", Integer.valueOf(predict_snsModel.getUserchaacteristics()));
            jsonPredict.accumulate("sns_notificationtype", Integer.valueOf(predict_snsModel.getNotificationtype()));
            jsonPredict.accumulate("sns_appname", Integer.valueOf(predict_snsModel.getAppname()));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JSONArray jsonArray2 = new JSONArray();
        jsonArray2.put(jsonPredict);


        JSONObject mainObj = new JSONObject();
        try {
            mainObj.put("data", jsonArray1);
            mainObj.put("predict", jsonArray2);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return mainObj;
    }

    private String setPostRequestContent(HttpURLConnection conn, JSONObject jsonObject) throws IOException {

        OutputStream os = conn.getOutputStream();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(jsonObject.toString());
        Log.i(MainStartPermissionActivity.class.toString(), jsonObject.toString());
        writer.flush();
        writer.close();


        InputStream in = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        //System.out.println(result.toString());

        // Log.v("inotify","jjjjjjjjjjjjjjjjjjj"+ result.toString());
        os.close();

        return result.toString();
    }


}
