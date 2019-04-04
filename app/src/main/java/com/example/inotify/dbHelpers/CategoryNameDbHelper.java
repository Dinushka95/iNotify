//package com.example.inotify.dbHelpers;
//
//import android.content.Context;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//
//import java.io.IOException;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class CategoryNameDbHelper extends MainDbHelp {
//
//
//    private static CategoryNameDbHelper mInstance = null;
//    private Context c1;
//    public CategoryNameDbHelper(Context context) {
//        super(context);
//        this.c1=context;
//
//
//    }
//    public static final String APP_URL = "https://play.google.com/store/apps/details?id=";
//    public static CategoryNameDbHelper getInstance(Context context) {
//
//        if (mInstance == null) {
//            mInstance = new CategoryNameDbHelper(context.getApplicationContext());
//        }
//        return mInstance;
//    }
//
//    public String getCategoryName( String appName )
//    {
//        String url = APP_URL + appName + "&hl=en";
//        Document doc = null;
//        try
//        {
//            doc = Jsoup.connect( url ).get();
//        } catch ( IOException e )
//        {
//            e.printStackTrace();
//        }
//
//        Element categoryElement = doc.selectFirst( "a[itemprop=genre]" );
//        if (categoryElement != null) {
//            return categoryElement.text();
//        }
//        return "";
//    }
//}
