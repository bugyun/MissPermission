package vip.ruoyun.permission.helper.check;

import android.Manifest;
import android.content.Context;
import android.os.Build;

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
public class StorageChecker implements IChecker {

    public static final String PERMISSION_NAME = "日历";

    static final int PERMISSION_ICONRES = R.drawable.miss_permission_ic_calendar;

    private IRomStrategy iRomStrategy;
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

    public StorageChecker(IRomStrategy iRomStrategy) {
        this.iRomStrategy = iRomStrategy;

    }

    @Override
    public boolean isCheckEnable(Context context, MissHelperConfiguration configuration) {
        return false;
    }

    @Override
    public String getPermissionName() {
        return "存储";
    }

    @Override
    public int getPermissionIconRes() {
        return R.drawable.miss_permission_ic_storage;
    }

    @Override
    public String[] getPermissions() {
        return NEED_PERMISSION;
    }
}
