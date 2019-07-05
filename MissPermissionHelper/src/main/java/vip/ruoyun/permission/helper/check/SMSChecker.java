package vip.ruoyun.permission.helper.check;

import android.Manifest;

/**
 * Created by ruoyun on 2019-06-25.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class SMSChecker {

    public static final String[] NEED_PERMISSION = {
            Manifest.permission.SEND_SMS,//
            Manifest.permission.RECEIVE_SMS,//
            Manifest.permission.READ_SMS,//
            Manifest.permission.RECEIVE_WAP_PUSH,//
            Manifest.permission.RECEIVE_MMS,//
    };

    public static boolean check() {


        return false;
    }
}
