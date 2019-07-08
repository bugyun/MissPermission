package vip.ruoyun.permission.helper.check;

import android.Manifest;
import android.content.Context;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import vip.ruoyun.permission.helper.core.IChecker;
import vip.ruoyun.permission.helper.core.MissHelperConfiguration;

/**
 * Created by ruoyun on 2019-06-25.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class LocationChecker implements IChecker {

    public final String[] NEED_PERMISSION = {
            Manifest.permission.ACCESS_FINE_LOCATION,//
            Manifest.permission.ACCESS_COARSE_LOCATION,//
    };

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public final String[] dd = {
            Manifest.permission.ACCESS_BACKGROUND_LOCATION,//后台定位权限
    };


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

    @Override
    public boolean isCheckEnable(Context context, MissHelperConfiguration configuration) {
        return false;
    }
}
