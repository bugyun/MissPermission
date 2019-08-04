package vip.ruoyun.permission.helper.check;

import android.Manifest;
import android.app.Activity;
import android.os.Environment;

import vip.ruoyun.permission.helper.R;
import vip.ruoyun.permission.helper.utils.AudioRecordManager;

/**
 * Created by ruoyun on 2019-07-05.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class MicrophoneChecker {
    private static final String TAG = "ContactsChecker";

    public static final String PERMISSION_NAME = "录音";

    public static final int PERMISSION_ICON_RES = R.drawable.miss_permission_ic_micro_phone;

    private final String[] NEED_PERMISSION = {
            Manifest.permission.RECORD_AUDIO,//
    };

    private static boolean checkRecordAudio(Activity activity) throws Exception {
        AudioRecordManager recordManager = new AudioRecordManager();

        recordManager.startRecord(activity.getExternalFilesDir(Environment.DIRECTORY_RINGTONES) + "/" +
                TAG + ".3gp");
        recordManager.stopRecord();

        return recordManager.getSuccess();
    }
}
