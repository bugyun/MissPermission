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
public class SensorsChecker {

    public static final String PERMISSION_NAME = "传感器";

    public static final int PERMISSION_ICON_RES = R.drawable.miss_permission_ic_sensors;

    private final String[] NEED_PERMISSION;

    public SensorsChecker() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            NEED_PERMISSION = new String[]{
                    Manifest.permission.BODY_SENSORS,//
            };
        } else {
            NEED_PERMISSION = new String[]{};
        }
    }
}
