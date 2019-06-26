package com.ruoyun.dpermission.check;

import android.content.Context;
import android.location.LocationManager;
import android.util.Log;

/**
 * Created by ruoyun on 2019-06-25.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class LocationChecker {


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
