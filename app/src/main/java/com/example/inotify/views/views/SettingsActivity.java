package com.example.inotify.views.views;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobScheduler;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inotify.R;
import com.example.inotify.logger.Log;
import com.example.inotify.logger.LogFragment;
import com.example.inotify.logger.LogWrapper;
import com.example.inotify.logger.MessageOnlyLogFilter;

import java.io.File;

import static com.example.inotify.dbHelpers.MainDbHelp.DATABASE_NAME;

public class SettingsActivity extends AppCompatActivity {
    private boolean mLogShown;
    long dbSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

                LogWrapper logWrapper = new LogWrapper();
                com.example.inotify.logger.Log.setLogNode(logWrapper);

                MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();
                logWrapper.setNext(msgFilter);

                LogFragment logFragment = (LogFragment) getSupportFragmentManager().findFragmentById(R.id.log_fragment);
                msgFilter.setNext(logFragment.getLogView());
                File f = this.getDatabasePath(DATABASE_NAME);
                dbSize = f.length()/1024;
                TextView textViewDbSize = findViewById(R.id.textViewdbsize);
                textViewDbSize.setText("Database Size is = "+String.valueOf(dbSize) + "KB");


    }

    public void button_resetDb(final View view) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Are You Sure To Reset Database");
        dialog.setTitle("Warning");
        dialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (view.getContext().deleteDatabase(DATABASE_NAME)) {
                            Toast.makeText(getApplicationContext(), "Database Successfully Reset", Toast.LENGTH_LONG).show();
                            restartApp();
                        } else {
                            Toast.makeText(getApplicationContext(), "Database Failed Reset", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //  Toast.makeText(getApplicationContext(),"cancel is clicked", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();


    }

    private void restartApp() {
        Intent intent = new Intent(getApplicationContext(), MainStartPermissionActivity.class);
        int mPendingIntentId = 18945;
        PendingIntent mPendingIntent = PendingIntent.getActivity(getApplicationContext(), mPendingIntentId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
        System.exit(0);
    }

    public void button_INotifyActiveApps(View view) {

    }


    public void cancelAllJobs(View view) {
        JobScheduler jobScheduler = (JobScheduler) this.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.cancelAll();
    }

    public void test(View view) {
        Log.d("inotify","XXXXXXXXXXXXXXXXXXXXXX");
    }


}
