package vip.ruoyun.permission.helper.check;

import android.Manifest;
import android.content.Context;

import vip.ruoyun.permission.helper.R;
import vip.ruoyun.permission.helper.core.IChecker;
import vip.ruoyun.permission.helper.core.IRomStrategy;
import vip.ruoyun.permission.helper.core.MissHelperConfiguration;

/**
 * Created by ruoyun on 2019-06-25.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class CalendarChecker implements IChecker {

    private IRomStrategy iRomStrategy;


    public CalendarChecker(IRomStrategy iRomStrategy) {
        this.iRomStrategy = iRomStrategy;
    }

    public static final String[] NEED_PERMISSION = {
            Manifest.permission.READ_CALENDAR,  //必选
            Manifest.permission.WRITE_CALENDAR,  //必选
    };

    public static final String PERMISSION_NAME = "日历";

    static final int PERMISSION_ICONRES = R.drawable.miss_permission_ic_calendar;

    @Override
    public boolean isCheckEnable(Context context, MissHelperConfiguration configuration) {
        return false;
    }

    public String getPermissionName() {
        return "日历";
    }

    public int getPermissionIconRes() {
        return PERMISSION_ICONRES;
    }

    @Override
    public String[] getPermissions() {
        return NEED_PERMISSION;
    }
}
