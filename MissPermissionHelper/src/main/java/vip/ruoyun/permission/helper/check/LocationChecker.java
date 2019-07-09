package vip.ruoyun.permission.helper.check;

import android.Manifest;
import android.content.Context;
import android.location.LocationManager;
import android.os.Build;
import android.util.Log;

import vip.ruoyun.permission.helper.core.IChecker;
import vip.ruoyun.permission.helper.core.IRomStrategy;
import vip.ruoyun.permission.helper.core.MissHelperConfiguration;

/**
 * Created by ruoyun on 2019-06-25.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class LocationChecker implements IChecker {


    private IRomStrategy iRomStrategy;
    private final String[] NEED_PERMISSION;

    public LocationChecker(IRomStrategy iRomStrategy) {
        this.iRomStrategy = iRomStrategy;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            NEED_PERMISSION = new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,//
                    Manifest.permission.ACCESS_COARSE_LOCATION,//
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION,//后台定位权限
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

    @Override
    public boolean isCheckEnable(Context context, MissHelperConfiguration configuration) {
        return false;
    }

    @Override
    public String getPermissionName() {
        return "定位";
    }

    @Override
    public int getPermissionIconRes() {
        return 0;
    }

    @Override
    public String[] getPermissions() {
        return NEED_PERMISSION;
    }
}
