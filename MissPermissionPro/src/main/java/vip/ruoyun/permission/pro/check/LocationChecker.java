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
public class LocationChecker {

    public static final String PERMISSION_NAME = "定位";

    public static final int PERMISSION_ICON_RES = R.drawable.miss_permission_ic_location;

    private final String[] NEED_PERMISSION;

    public LocationChecker() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            NEED_PERMISSION = new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,//
                    Manifest.permission.ACCESS_COARSE_LOCATION,//
//                    Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                    "android.permission.ACCESS_BACKGROUND_LOCATION",//后台定位权限
            };
        } else {
            NEED_PERMISSION = new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,//
                    Manifest.permission.ACCESS_COARSE_LOCATION,//
            };
        }
    }
}
