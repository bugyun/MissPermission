package vip.ruoyun.permission.pro.check;

import android.Manifest;
import android.os.Build;
import vip.ruoyun.permission.pro.R;

/**
 * Created by ruoyun on 2019-07-05.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class PhoneChecker {

    public static final String PERMISSION_NAME = "设备信息";

    public static final int PERMISSION_ICON_RES = R.drawable.miss_permission_ic_phone;

    //    CallLogChecker
    private final String[] NEED_PERMISSION;

    public PhoneChecker() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NEED_PERMISSION = new String[]{
                    Manifest.permission.READ_PHONE_STATE,//
                    Manifest.permission.READ_PHONE_NUMBERS,//
                    Manifest.permission.CALL_PHONE,//
                    Manifest.permission.ANSWER_PHONE_CALLS,//
                    Manifest.permission.WRITE_CALL_LOG,//
                    Manifest.permission.ADD_VOICEMAIL,//
                    Manifest.permission.USE_SIP,//
                    Manifest.permission.PROCESS_OUTGOING_CALLS,//
            };

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            NEED_PERMISSION = new String[]{
                    Manifest.permission.READ_PHONE_STATE,//
                    Manifest.permission.CALL_PHONE,//
                    Manifest.permission.WRITE_CALL_LOG,//
                    Manifest.permission.ADD_VOICEMAIL,//
                    Manifest.permission.USE_SIP,//
                    Manifest.permission.PROCESS_OUTGOING_CALLS,//
            };
        } else {
            NEED_PERMISSION = new String[]{
                    Manifest.permission.READ_PHONE_STATE,//
                    Manifest.permission.CALL_PHONE,//
                    Manifest.permission.ADD_VOICEMAIL,//
                    Manifest.permission.USE_SIP,//
                    Manifest.permission.PROCESS_OUTGOING_CALLS,//
            };
        }
    }
}




