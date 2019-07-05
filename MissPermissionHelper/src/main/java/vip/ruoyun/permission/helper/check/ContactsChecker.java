package vip.ruoyun.permission.helper.check;

import android.Manifest;

/**
 * Created by ruoyun on 2019-06-25.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class ContactsChecker {


    public static final String[] NEED_PERMISSION = {
            Manifest.permission.READ_CONTACTS,//
            Manifest.permission.WRITE_CONTACTS,//
            Manifest.permission.GET_ACCOUNTS,//
    };


    public static boolean read() {
        return false;
    }
}
