package com.example.inotify.configs;

public class TbColNames {

    // if there is duplicate entries please don't delete them
    // under each table you should incrase all the table columns and comment for duplicates

    ////notification
    public static final String NOTIFICATIONID = "notificationid";
    public static final String DATE = "date";
    public static final String TIMERECEVIED = "timeRecevied";
    public static final String TIMESENT = "timeSent";
    public static final String TIMEVIEW = "timeView";
    public static final String APPNAME = "appName";
    public static final String PACKAGENAME = "packageName";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String SMARTNOTIFICATION = "smartNotification";


    //PRASHAN
    ////nv sql lite db helper
    //public static final String DATE = "date";
    public static final String PROID = "probability_id";
    public static final String DAY = "day";
    public static final String TIME = "time";
    public static final String ACTIVITY = "activity";
    public static final String LOCATION = "location";
    public static final String BUSYORNOT = "busyornot";
    public static final String TYPE = "type";
    public static final String CONFIDENCE = "confidence";
    public static final String LOG = "log";
    public static final String LAT = "lat";
    public static final String VIEWOR = "viewor";
    public static final String NOTOR = "notor";
    //public static final String DAY = "day";
    public static final String PROBABLITY = "probability";
    public static final String PROBABLITY_ID = "probability";

    //PROBABILITYQUERY_TABLE
    public static final String TIME_SLOT = "time_slot";
    public static final String VIEWORSUM = "vieworsum";
    public static final String NOTORSUM = "notorsum";
    public static final String PROBABILITYFINAL = "probabilityfinal";


    //// ringer mode
    public static final String RM_NOTIFICATIONID = "rm_notificationid";
    public static final String RM_DAY = "rm_day";
    public static final String RM_DATE = "rm_date";
    public static final String RM_TIME = "rm_time";
    public static final String RM_RINGERMODE ="rm_ringermode";

    ////screenStatus
    public static final String SCREENSTATUS_ID = "screenstatusid";
    //public static final String DATE = "date";
    //public static final String NOTIFICATIONID = "notificationid";
    public static final String TIMEON = "timeon";
    public static final String TIMEOFF = "timeoff";


    ////NotificationImportance
    public static final String NOTIFICATIONIID = "notificationiid";
    public static final String APPLICATIONNAME = "applicationname";
    public static final String SEQUENCEVALUE ="sequencevalue";


    ////MainUserAttentivness
    public static final String NID ="nid";
    public static final String APPLICATION ="application";
    public static final String ATTENTIVNESSVALUE = "attentivnessvalue";

    //AttentivnesssperApp
    ////public static final String APPNAME ="appname";
    public static final String TOTALATTENTIVNESS = "totalattentivness";
    public static final String TOTALATTENTIVNESSPERCENTAGE = "totalattentivnesspercentage";

    ////
    public static final String TIMESLOT = "timeslot";
    public static final String ATTENTIVNESSPERHOUR = "attentivnessperhour";


    ////smart notification system
    // notification all notifications
    public static final String SNS_ID = "sns_id";
    public static final String SNS_DATE = "sns_date";
    public static final String SNS_DAY = "sns_day";
    public static final String SNS_TIME = "sns_time";
    public static final String SNS_BUSYORNOT = "sns_busyornot";
    public static final String SNS_ATTENTIVINESS = "sns_attentiviness";
    public static final String SNS_USERCHAACTERISTICS = "sns_userchaacteristics";
    public static final String SNS_NOTIFICATIONTYPE = "sns_notificationtype";
    public static final String SNS_APPNAME = "sns_appname";
    public static final String SNS_VTIME = "sns_vtime";

    ////topApps
    public static final String TOPAPP_ID = "topapp_id";
    //public static final String DATE = "date";
    // public static final String APPNAME = "appname";
    //public static final String PACKAGENAME = "packageName";
    public static final String APPCOLLECTION = "appcollection";
    public static final String DATECREATED = "datecreated";
    public static final String APPCATEGORY = "appcategory";
    public static final String RANK = "rank";




    //// notification importance value
    public static final String NI_APPNAME = "NI_APPNAME";
    public static final String NI_VALUE = "NI_VALUE";

    //// uc sql lite db helper
    // public static final String APPNAME = "appname";
    // private static final String DATE = "date";
    public static final String COUNT = "count";

    //// profile  user
    public static final String PROFILE_ID = "profileId";
    //public static final String DATE = "date";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String GENDER = "gender";
    public static final String OCCUPATION = "occupation";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";


    ////charger table
    public static final String CHARGERID="chargeId";
    public static final String POWERONDATE="powerOnDate";
    public static final String POWERONTIME="powerOnTime";
    public static final String POWEROFFDATE="powerOffDate";
    public static final String POWEROFFTIME="powerOffTime";

    ////appusage table
    public static final String APPUSAGEID="appUsageId";
    //public static final String DATE="date";
    //public static final String TIME="time";
    //public static final String PACKAGENAME="packageName";
    //public static final String APPNAME="appName";
    //public static final String APPCATEGORY="appCategory";
    public static final String USAGETIME="UsageTime";

    //Application count
    public static final String TOPAPPCOUNT_ID = "topappcount_ID";
    public static final String SOCIALAPPCOUNT = "socialappcount";
    public static final String GAMINGAPPCOUNT = "gamingappcount";
    public static final String EDUCATIONAPPCOUNT = "educationappcount";
    public static final String DATINGAPPCOUNT = "datingappcount";
    public static final String MUSICVIDEOAPPCOUNT = "musicvideoappcount";
    public static final String COMMUNICATIONAPPCOUNT ="communicationappcount";


    //Application count
    public static final String APPCOUNT_ID = "account_ID";
    //public static final String SOCIALAPPCOUNT = "socialappcount";
    //public static final String GAMINGAPPCOUNT = "gamingappcount";
    //public static final String EDUCATIONAPPCOUNT = "educationappcount";
    //public static final String DATINGAPPCOUNT = "datingappcount";
    //public static final String MUSICVIDEOAPPCOUNT = "musicvideoappcount";
    //public static final String COMMUNICATIONAPPCOUNT ="communicationappcount";

    //Application  table
    public static final String APPLICATION_ID = "applicationId";
    //public static final String APPNAME = "appname";
    //public static final String APPCATEGORY = "appcategory";
    //public static final String PACKAGENAME = "packageName";


    //CallDuration table
    public static final String CALLDURATION_ID = "calldurationId";
    public static final String DURATION ="duration";

    //ContactCount table
    public static final String CONTACTCOUNT_ID ="contactcountId";

    //ATTRIBUTECOUNT_TABLE
    public static final String ATTRIBUTECOUNT_ID = "attributecountId";
    public static final String APPCOUNT = "appcount";
    public static final String SCREENONTIMECOUNT = "scrrenontimecount";
    public static final String CHARGINGCOUNT = "chargingcount";
    public static final String CONTACTCOUNT = "contactcount";

    //iNotifyActiveApps table
    public static final String INOTIFYACTIVEACTIVEAPPS_ID = "iNotifyActiveAppsId";
    public static final String STATUS = "status";
    //public static final String PACKAGENAME = "packageName";

    //OPENNESS_table
    public static final String OPENNESS_ID = "opennessId";
    public static final String NEWLYAPPS = "newlyapps";
    public static final String ALLAPPUSAGE = "allappusage";
    public static final String SOCIALAPPUSAGE = "socialappusage";
    public static final String NOOFSOCIALAPPS = "noofsocialapps";
    public static final String NOOFCOMMUNICTIONAPPS = "noofcommunicationapps";
    public static final String COMMUNICATIONAPPUSAGE = "communicationappusage";

    //CONSCIENTIOUSNESS_table
    public static final String CONSCIENTIOUSNESS_ID = "conscientiounessId";
    public static final String MUSICVIDEOAPPUSAGE = "musicvideoappusage";
    public static final String MUSICVIDEOAPP = "musicvideoapp";
    public static final String PHOTOGRAPYAPPUSAGE = "photograpyappusage";
    public static final String PHOTOGRAPYAPP = "photograpyapp";
    public static final String CALENDEREVENT = "calenderevent";
    public static final String CHARGE = "charge";

    //CHARACTERISTICSFINAL_TABLE
    public static final String CHARACTERISTICSfINAL_ID = "characteristicId";
    public static final String OPENNESS = "openness";
    public static final String CONSCIENTIOUSNESS = "conscientiousness";
    public static final String EXTRAVERSION = "extraversion";
    public static final String NEUROTICISM = "neuroticism";
    public static final String AGREEABLENESS = "agreeableness";

    //DATAUSAGE_TABLE
    public static final String DATAUSAGE_ID = "datausageId";
    //public static final String DATE = "date";
    public static final String AMOUNT = "amount";

    //COMMONAPPCOUNT_TABLE
    public static final String COMMONAPPCOUNT_ID = "communicationId";
    public static final String SOCIAL = "social";
    public static final String GAMING = "gaming";
    public static final String PHOTOGRAPHY = "photograpy";
    public static final String PERSONALIZATION = "personalization";
    public static final String COMMUNICATION = "communication";
    public static final String TOOLS = "tools";
    public static final String MUSICVIDEO = "musicVideo";




}
