package vip.ruoyun.permission.helper.check;

import android.Manifest;
import android.content.Context;

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

    private String[] NEED_PERMISSION = {
            Manifest.permission.READ_CALENDAR,  //必选
            Manifest.permission.WRITE_CALENDAR,  //必选
    };

    @Override
    public boolean isCheckEnable(Context context, MissHelperConfiguration configuration) {
        return false;
    }

    public String getPermissionName() {
        return "日历";
    }

    public int getPermissionIconRes() {
        return 1;
    }

    @Override
    public String[] getPermissions() {
        return NEED_PERMISSION;
    }
}
