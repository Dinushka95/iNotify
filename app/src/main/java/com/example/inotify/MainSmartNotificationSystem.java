package com.example.inotify;

import android.content.Context;
import android.util.Log;

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

    Din_SqlLiteDbHelper din_sqlLiteDbHelper;
    Din_SNSModel predict_snsModel ;

    public MainSmartNotificationSystem(Context context,Din_SNSModel din_snsModel) {
        din_sqlLiteDbHelper=new Din_SqlLiteDbHelper(context);
        predict_snsModel = din_snsModel;
    }

    public String  getPrediction(){

        String cc="";
        try {
            cc = HttpPost("https://us-central1-inotify23.cloudfunctions.net/hello_http");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.v("inotify","rrrrrrrrrrrrrr"+cc);

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
        result=setPostRequestContent(conn, jsonObject);

        // 4. make POST request to the given URL
        conn.connect();

        // 5. return response message
        conn.getResponseMessage();


        return result;

    }

    private JSONObject buidJsonObject() {

        ArrayList<Din_SNSModel>  din_snsModels = new ArrayList<>();

        JSONArray jsonArray1= new JSONArray();
        //all data
        din_snsModels = din_sqlLiteDbHelper.getALL();

        int count =din_snsModels.size();

        for (Din_SNSModel x:din_snsModels){
            JSONObject jsonData1 = new JSONObject();
            try {
                jsonData1.accumulate("sns_day", Integer.valueOf(x.getDay()) );
                jsonData1.accumulate("sns_time", Integer.valueOf(x.getTime()));
                jsonData1.accumulate("sns_busyornot", Integer.valueOf(x.getBusyornot()));
                jsonData1.accumulate("sns_attentiviness", Integer.valueOf(x.getAttentiviness()));
                jsonData1.accumulate("sns_userchaacteristics", Integer.valueOf(x.getUserchaacteristics()));
                jsonData1.accumulate("sns_notificationtype", Integer.valueOf(x.getNotificationtype()));
                jsonData1.accumulate("sns_appname", Integer.valueOf(x.getAppname()));
                jsonData1.accumulate("sns_vtime", Integer.valueOf(x.getVtime()));


            } catch (JSONException e) {
                e.printStackTrace();
            }

            jsonArray1.put(jsonData1);
        }

        JSONObject jsonPredict = new JSONObject();

        try {

            jsonPredict.accumulate("sns_day",Integer.valueOf( predict_snsModel.getDay()));
            jsonPredict.accumulate("sns_time", Integer.valueOf(predict_snsModel.getTime()));
            jsonPredict.accumulate("sns_busyornot", Integer.valueOf(predict_snsModel.getBusyornot()));
            jsonPredict.accumulate("sns_attentiviness", Integer.valueOf(predict_snsModel.getAttentiviness()));
            jsonPredict.accumulate("sns_userchaacteristics", Integer.valueOf(predict_snsModel.getUserchaacteristics()));
            jsonPredict.accumulate("sns_notificationtype", Integer.valueOf(predict_snsModel.getNotificationtype()));
            jsonPredict.accumulate("sns_appname",Integer.valueOf( predict_snsModel.getAppname()));

        } catch (JSONException e) {
            e.printStackTrace();
        }





        JSONArray jsonArray2= new JSONArray();
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
        Log.i(MainActivity.class.toString(), jsonObject.toString());
        writer.flush();
        writer.close();


        InputStream in =conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            result.append(line);
        }
        //System.out.println(result.toString());

        // Log.v("inotify","jjjjjjjjjjjjjjjjjjj"+ result.toString());
        os.close();

        return result.toString();
    }


}
