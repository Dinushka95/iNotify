package com.example.inotify.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.inotify.R;

import static com.example.inotify.dbHelpers.MainSqlliteOpenHelp.DATABASE_NAME;

public class settingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void resetDb(final View view) {

        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Are You Sure To Reset Database");
        dialog.setTitle("Warning");
        dialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(view.getContext().deleteDatabase(DATABASE_NAME)){
                            Toast.makeText(getApplicationContext(),"Database Successfully Reset",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Database Failed Reset",Toast.LENGTH_LONG).show();
                        }
                    }
                });
        dialog.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              //  Toast.makeText(getApplicationContext(),"cancel is clicked", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();


    }

    public void buttonInotifyActiveApps(View view) {
        Intent intent = new Intent(settingsActivity.this, iNotifiyActiviteAppsActivity.class);
        startActivity(intent);
    }


}
