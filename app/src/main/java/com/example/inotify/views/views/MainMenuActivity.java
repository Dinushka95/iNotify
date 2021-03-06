package com.example.inotify.views.views;

import android.app.Dialog;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inotify.R;
import com.example.inotify.configs.MyConstants;
import com.example.inotify.helpers.ProfileHelper;
import com.example.inotify.helpers.ScreenStatusHelper;
import com.example.inotify.models.ProfileModel;
import com.example.inotify.services.ActivityRecognitionService;
import com.example.inotify.services.LocationService;
import com.example.inotify.services.NotificationViewabilityService;
import com.example.inotify.services.UserCharacteristics_service;
import com.example.inotify.viewControllers.adapters.MainMenuPagerAdapter;
import com.example.inotify.views.fragments.TabAllNotificationsFragment;
import com.example.inotify.views.fragments.TabApplicationFragment;
import com.example.inotify.views.fragments.TabDashBoardFragment;
import com.example.inotify.views.fragments.TabPendingNotificationsFragment;
import com.example.inotify.views.fragments.TabSmartNotificationFragment;
import com.example.inotify.views.fragments.TabUserCharacteristicsFragment;
import com.google.android.gms.location.ActivityRecognitionClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainMenuActivity extends AppCompatActivity implements
        TabDashBoardFragment.OnFragmentInteractionListener,
        TabPendingNotificationsFragment.OnFragmentInteractionListener,
        TabSmartNotificationFragment.OnFragmentInteractionListener,
        TabAllNotificationsFragment.OnFragmentInteractionListener,
        TabApplicationFragment.OnFragmentInteractionListener,
        TabUserCharacteristicsFragment.OnFragmentInteractionListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    TextView textViewProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

/*        stopService(new Intent(this, MyNotificationListenerService.class));


        startService(new Intent(this, MyNotificationListenerService.class));*/



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        drawerLayout = findViewById(R.id.main_layout);
 /*        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();

        View headerView = navigationView.getHeaderView(0);
        textViewProfile = (TextView) headerView.findViewById(R.id.profilename);

        ImageView imageView = findViewById(R.id.actionbarimageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(menuItem -> {
            // set item as selected to persist highlight

            switch (menuItem.getItemId()) {
                case R.id.nav_b_smart_notifications:
                    Toast.makeText(MainMenuActivity.this, "clicked ac2", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_b_userattention:
                    Intent intent = new Intent(MainMenuActivity.this, UserAttentivenessActivity.class);
                    startActivity(intent);
                    break;
                case R.id.nav_b_usercharacteristics:
                    Intent intent2 = new Intent(MainMenuActivity.this, UsercharacteristicsActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.nav_b_notificationviewability:
                    Intent intent4 = new Intent(MainMenuActivity.this, NotificationViewabilityActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.nav_b_settings:
                    Intent intent1 = new Intent(MainMenuActivity.this, SettingsActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.nav_b_exit:
                    System.exit(0);
                    break;
            }


            menuItem.setChecked(true);

            // close drawer when item is tapped
            drawerLayout.closeDrawers();

            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here

            return true;
        });

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });


        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TabDashBoardFragment(), "Dash\nBoard");
        adapter.addFragment(new TabPendingNotificationsFragment(), "Pending\nNotification");
        adapter.addFragment(new TabSmartNotificationFragment(), "Smart \n Notification");
        adapter.addFragment(new TabAllNotificationsFragment(), "All \n Notification");
        adapter.addFragment(new TabApplicationFragment(), "Active\nApplications");
        adapter.addFragment(new TabUserCharacteristicsFragment(), "User \n Characteristics");

        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        if (MyConstants.PERMISSION_CONTACTS &&
                MyConstants.PERMISSION_LOCATION &&
                MyConstants.PERMISSION_CALENDER &&
                MyConstants.PERMISSION_PHONE &&
                MyConstants.PERMISSION_NOTIFICATIONACCESS &&
                MyConstants.PERMISSION_USEAGEACCESS)
        {
            tt();
        } else {
            Toast.makeText(getApplicationContext(), "You Have Not Given Proper Access Permission.Please give Permission", Toast.LENGTH_LONG).show();
        }

    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }


    public void tt() {

        ActivityRecognitionClient mActivityRecognitionClient = new ActivityRecognitionClient(this);
        mActivityRecognitionClient.requestActivityUpdates(0, getActivityDetectionPendingIntent());


        ComponentName componentName = new ComponentName(MainMenuActivity.this, LocationService.class);
        JobInfo info = new JobInfo.Builder(MyConstants.MY_LOCATION_LISTENER_SERVEC_ID, componentName)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(9 * 57 * 1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler != null ? scheduler.schedule(info) : 0;


        ComponentName componentName1 = new ComponentName(MainMenuActivity.this, NotificationViewabilityService.class);
        JobInfo info1 = new JobInfo.Builder(MyConstants.MY_BUSYORNOT_SERVEC_ID, componentName1)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(10 * 60 * 1000)
                .build();

        JobScheduler scheduler1 = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode1 = scheduler1 != null ? scheduler1.schedule(info1) : 0;


        ComponentName componentName2 = new ComponentName(MainMenuActivity.this, UserCharacteristics_service.class);
        JobInfo info2 = new JobInfo.Builder(MyConstants.MY_MIT_ALL_SERVEC_ID, componentName2)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(24 * 60 * 60 * 1000)
                .build();

        JobScheduler scheduler2 = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode2 = scheduler2.schedule(info2);

        ScreenStatusHelper screenStatusHelper = new ScreenStatusHelper();
        screenStatusHelper.start(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        // if only the user  does not exist
        ProfileHelper profileHelper = new ProfileHelper(this);
        if (profileHelper.profileisExisCheck()) {
            //load settings
            if(profileHelper.profileisExisCheck()) {
                ProfileModel profileModel = profileHelper.get();
                textViewProfile.setText(profileModel.getName());
            }
        } else {
            //show signup
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.signup);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            ImageButton imageButton = dialog.findViewById(R.id.ib_close);
            imageButton.setOnClickListener(v -> System.exit(0));
            Button button = dialog.findViewById(R.id.button11);
            button.setOnClickListener(view -> {
                // save to db -- create a new profile
                String datenow = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

                EditText editText = dialog.findViewById(R.id.editText);
                EditText editText2 = dialog.findViewById(R.id.editText2);
                RadioButton radioButton = dialog.findViewById(R.id.radioButton);
                RadioButton radioButton2 = dialog.findViewById(R.id.radioButton2);
                EditText editText3 = dialog.findViewById(R.id.editText3);
                EditText editText4 = dialog.findViewById(R.id.editText4);
                EditText editText5 = dialog.findViewById(R.id.editText5);

                String gender = "";

                if (radioButton.isSelected()) {
                    gender = "Male";
                }
                if (radioButton2.isSelected()) {
                    gender = "Female";
                }

                if (profileHelper.insert(new ProfileModel(
                        datenow,
                        editText.getText().toString(),
                        editText2.getText().toString(),
                        gender,
                        editText3.getText().toString(),
                        editText4.getText().toString(),
                        editText5.getText().toString()
                ))) {
                    //if sucessfull
                    Toast.makeText(getApplicationContext(), "Successfully Saved User Details", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    if(profileHelper.profileisExisCheck()) {
                        ProfileModel profileModel = profileHelper.get();
                        textViewProfile.setText(profileModel.getName());
                    }

                } else {
                    //if failed
                    Toast.makeText(getApplicationContext(), "Failed To Save User Details", Toast.LENGTH_LONG).show();
                }
            });
            dialog.show();
        }
    }

    private PendingIntent getActivityDetectionPendingIntent() {
        return PendingIntent.getService(this, 30, new Intent(this, ActivityRecognitionService.class), PendingIntent.FLAG_UPDATE_CURRENT);
    }


    public void button_userprofile(View view) {
        Intent intent = new Intent(MainMenuActivity.this, UserProfileActivity.class);
        startActivity(intent);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }



}

