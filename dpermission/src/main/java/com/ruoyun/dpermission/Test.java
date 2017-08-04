package com.ruoyun.dpermission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;


/**
 * Created by fanpu on 2017/8/4.
 * 权限
 */
public class Test {

    /**
     * Android 6.0
     */
    public void test(Activity activity) {
        requestPermission(activity, Manifest.permission.READ_CALENDAR, 121212);
    }

    private void requestPermission(Activity activity, String permission, int requestCode) {
        if (!isGranted(activity, permission)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {

            } else {
                ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
            }
        } else {
            //直接执行相应操作了
        }
    }

    public boolean isGranted(Context context, String permission) {
        return !isMarshmallow() || isGranted_(context, permission);
    }

    private boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    private boolean isGranted_(Context context, String permission) {
        int checkSelfPermission = ActivityCompat.checkSelfPermission(context, permission);
        return checkSelfPermission == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 危险权限和权限组。
     * https://developer.android.com/guide/topics/security/permissions.html?hl=zh-cn#defining
     */
    public String[] permisss = {
            //1.calendar权限组
            Manifest.permission.READ_CALENDAR,//
            Manifest.permission.WRITE_CALENDAR,//
            //2.camera权限组
            Manifest.permission.CAMERA,//
            //3.contacts权限组
            Manifest.permission.READ_CONTACTS,//
            Manifest.permission.WRITE_CONTACTS,//
            Manifest.permission.GET_ACCOUNTS,//
            //4.location权限组
            Manifest.permission.ACCESS_FINE_LOCATION,//
            Manifest.permission.ACCESS_COARSE_LOCATION,//
            //5.microphone权限组
            Manifest.permission.RECORD_AUDIO,//
            //6.phone权限组
            Manifest.permission.READ_PHONE_STATE,//
            Manifest.permission.CALL_PHONE,//
            Manifest.permission.READ_CALL_LOG,//api16以上
            Manifest.permission.WRITE_CALL_LOG,//api16以上
            Manifest.permission.ADD_VOICEMAIL,//
            Manifest.permission.USE_SIP,//
            Manifest.permission.PROCESS_OUTGOING_CALLS,//
            //7.sms权限组
            Manifest.permission.SEND_SMS,//
            Manifest.permission.RECEIVE_SMS,//
            Manifest.permission.READ_SMS,//
            Manifest.permission.RECEIVE_WAP_PUSH,//
            Manifest.permission.RECEIVE_MMS,//
            //8.storage权限组
            Manifest.permission.READ_EXTERNAL_STORAGE,//
            Manifest.permission.WRITE_EXTERNAL_STORAGE,//
    };


}
