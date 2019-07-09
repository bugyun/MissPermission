package vip.ruoyun.permission.helper.check;

import android.Manifest;
import android.content.Context;

import vip.ruoyun.permission.helper.R;
import vip.ruoyun.permission.helper.core.IChecker;
import vip.ruoyun.permission.helper.core.MissHelperConfiguration;

/**
 * Created by ruoyun on 2019-07-05.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class MicrophoneChecker implements IChecker {

    public static final String PERMISSION_NAME = "日历";

    static final int PERMISSION_ICONRES = R.drawable.miss_permission_ic_calendar;

    private final String[] NEED_PERMISSION = {
            Manifest.permission.RECORD_AUDIO,//
    };

    @Override
    public boolean isCheckEnable(Context context, MissHelperConfiguration configuration) {
        return true;
    }

    @Override
    public String getPermissionName() {
        return "录音";
    }

    @Override
    public int getPermissionIconRes() {
        return R.drawable.miss_permission_ic_micro_phone;
    }

    @Override
    public String[] getPermissions() {
        return NEED_PERMISSION;
    }
}
