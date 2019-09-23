package vip.ruoyun.permission.pro.check;

import android.Manifest;
import vip.ruoyun.permission.pro.R;

/**
 * Created by ruoyun on 2019-06-25.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class SMSChecker {

    public static final String PERMISSION_NAME = "信息";

    public static final int PERMISSION_ICON_RES = R.drawable.miss_permission_ic_sms;

    public static final String[] NEED_PERMISSION = {
            Manifest.permission.SEND_SMS,//
            Manifest.permission.RECEIVE_SMS,//
            Manifest.permission.READ_SMS,//
            Manifest.permission.RECEIVE_WAP_PUSH,//
            Manifest.permission.RECEIVE_MMS,//
    };

}
