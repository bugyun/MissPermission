package vip.ruoyun.permission.pro.check;

import android.Manifest;
import vip.ruoyun.permission.pro.R;

/**
 * Created by ruoyun on 2019-06-25.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class ContactsChecker {

    private static final String TAG = "ContactsChecker";

    public static final String PERMISSION_NAME = "联系人";

    public static final int PERMISSION_ICON_RES = R.drawable.miss_permission_ic_contacts;

    public static final String[] NEED_PERMISSION = {
            Manifest.permission.READ_CONTACTS,//
            Manifest.permission.WRITE_CONTACTS,//
            Manifest.permission.GET_ACCOUNTS,//
    };
}
