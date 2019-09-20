package vip.ruoyun.permission.pro.check;

import android.Manifest;
import android.os.Build;
import vip.ruoyun.permission.pro.R;

/**
 * Created by ruoyun on 2019-06-25.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class StorageChecker {

    public static final String PERMISSION_NAME = "存储";

    public static final int PERMISSION_ICON_RES = R.drawable.miss_permission_ic_storage;

    public static final String[] NEED_PERMISSION;

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            NEED_PERMISSION = new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,//
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,//
            };
        } else {
            NEED_PERMISSION = new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,//
            };
        }
    }
}
