package vip.ruoyun.permission.helper.check;

import android.Manifest;

/**
 * Created by ruoyun on 2019-07-05.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class CallLogChecker {

    public static final String[] FM_PERMISSION = {
            Manifest.permission.READ_CALL_LOG,  //必选
            Manifest.permission.WRITE_CALL_LOG,  //必选
            Manifest.permission.PROCESS_OUTGOING_CALLS,  //必选
    };


}
