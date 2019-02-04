package com.example.inotify;

import android.content.Context;
import android.media.AudioManager;

public class RingerMode {

    public String getRingerMode(Context context){
        String ringerMode = "";
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        switch (am.getRingerMode()) {
            case AudioManager.RINGER_MODE_SILENT:
                ringerMode = "silent";
                break;
            case AudioManager.RINGER_MODE_VIBRATE:
                ringerMode = "vibrate";
                break;
            case AudioManager.RINGER_MODE_NORMAL:
                ringerMode = "normal";
                break;
        }
        return ringerMode;
    }
}
