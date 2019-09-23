package vip.ruoyun.permission.pro.check;

import android.Manifest;
import vip.ruoyun.permission.pro.R;

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
}
