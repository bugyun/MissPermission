package vip.ruoyun.permission.helper.check;

import android.Manifest;
import android.content.Context;
import android.os.Build;

import vip.ruoyun.permission.helper.R;
import vip.ruoyun.permission.helper.core.IChecker;
import vip.ruoyun.permission.helper.core.IRomStrategy;
import vip.ruoyun.permission.helper.core.MissHelperConfiguration;

/**
 * Created by ruoyun on 2019-07-05.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class SensorsChecker implements IChecker {

    public static final String PERMISSION_NAME = "日历";

    static final int PERMISSION_ICONRES = R.drawable.miss_permission_ic_calendar;

    private IRomStrategy iRomStrategy;
    private final String[] NEED_PERMISSION;

    public SensorsChecker(IRomStrategy iRomStrategy) {
        this.iRomStrategy = iRomStrategy;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            NEED_PERMISSION = new String[]{
                    Manifest.permission.BODY_SENSORS,//
            };
        } else {
            NEED_PERMISSION = new String[]{};
        }
    }

    @Override
    public boolean isCheckEnable(Context context, MissHelperConfiguration configuration) {
        return false;
    }

    @Override
    public String getPermissionName() {
        return "传感器";
    }

    @Override
    public int getPermissionIconRes() {
        return R.drawable.miss_permission_ic_sensors;
    }

    @Override
    public String[] getPermissions() {
        return NEED_PERMISSION;
    }
}
