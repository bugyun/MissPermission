package com.ruoyun.dpermission;

import android.Manifest;

/**
 * Created by fanpu on 2017/8/4.
 */

public class PermissionType {
    /**
     * 危险权限和权限组。
     * https://developer.android.com/guide/topics/security/permissions.html?hl=zh-cn#defining
     */
    public String[] CalendarPermission = {
            //1.calendar权限组
            Manifest.permission.READ_CALENDAR,//
            Manifest.permission.WRITE_CALENDAR,//
    };

    public static String[] CameraPermission = {
            //2.camera权限组
            Manifest.permission.CAMERA,//
    };

    public static String[] ContactsPermission = {
            //3.contacts权限组
            Manifest.permission.READ_CONTACTS,//
            Manifest.permission.WRITE_CONTACTS,//
            Manifest.permission.GET_ACCOUNTS,//
    };

    public static String[] LocationPermission = {
            //4.location权限组
            Manifest.permission.ACCESS_FINE_LOCATION,//
            Manifest.permission.ACCESS_COARSE_LOCATION,//
    };

    public static String[] MicrophonePermission = {
            //5.microphone权限组
            Manifest.permission.RECORD_AUDIO,//
    };

    public static String[] PhonePermission = {
            //6.phone权限组
            Manifest.permission.READ_PHONE_STATE,//
            Manifest.permission.CALL_PHONE,//
            Manifest.permission.READ_CALL_LOG,//api16以上
            Manifest.permission.WRITE_CALL_LOG,//api16以上
            Manifest.permission.ADD_VOICEMAIL,//
            Manifest.permission.USE_SIP,//
            Manifest.permission.PROCESS_OUTGOING_CALLS,//
    };

    public static String[] SmsPermission = {
            //7.sms权限组
            Manifest.permission.SEND_SMS,//
            Manifest.permission.RECEIVE_SMS,//
            Manifest.permission.READ_SMS,//
            Manifest.permission.RECEIVE_WAP_PUSH,//
            Manifest.permission.RECEIVE_MMS,//
    };

    public static String[] StoragePermission = {
            //8.storage权限组
            Manifest.permission.READ_EXTERNAL_STORAGE,//api16以上
            Manifest.permission.WRITE_EXTERNAL_STORAGE,//
    };

}
