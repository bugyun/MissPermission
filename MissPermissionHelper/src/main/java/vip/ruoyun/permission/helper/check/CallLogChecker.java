package vip.ruoyun.permission.helper.check;

import android.Manifest;
import android.os.Build;

import vip.ruoyun.permission.helper.R;

/**
 * Created by ruoyun on 2019-07-05.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class CallLogChecker {

    public static final String PERMISSION_NAME = "通话记录";

    static final int PERMISSION_ICON_RES = R.drawable.miss_permission_ic_phone;


    private final String[] NEED_PERMISSION;

    public CallLogChecker() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            NEED_PERMISSION = new String[]{
                    Manifest.permission.READ_CALL_LOG,  //必选
                    Manifest.permission.WRITE_CALL_LOG,  //必选
                    Manifest.permission.PROCESS_OUTGOING_CALLS,  //必选
            };
        } else {
            NEED_PERMISSION = new String[]{
                    Manifest.permission.PROCESS_OUTGOING_CALLS,  //必选
            };
        }
    }
}
