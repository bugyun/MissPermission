package vip.ruoyun.permission.helper.check;

import android.Manifest;
import android.content.Context;
import android.location.LocationManager;
import android.os.Build;
import android.util.Log;

import vip.ruoyun.permission.helper.R;

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

    /**
     * 检测权限
     * true:有权限，false :没有此权限
     *
     * @return 是否有权限
     */
    public static boolean check(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean ok = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);//GPS定位服务是否开启
        if (!ok) {
            Log.e("zyh", "GPS定位服务未开启，请打开定位服务");
            return false;
        }

//        boolean ok = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);


        return false;
    }
}
