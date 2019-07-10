package vip.ruoyun.permission.helper.check;

import android.Manifest;
import android.content.Context;
import android.os.Build;

import vip.ruoyun.permission.helper.R;
import vip.ruoyun.permission.helper.core.IChecker;
import vip.ruoyun.permission.helper.core.MissHelperConfiguration;

/**
 * Created by ruoyun on 2019-07-05.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class CallLogChecker implements IChecker {

    public static final String PERMISSION_NAME = "日历";

    static final int PERMISSION_ICONRES = R.drawable.miss_permission_ic_calendar;


    private final String[] NEED_PERMISSION;

    public CallLogChecker() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            NEED_PERMISSION = new String[]{
                    Manifest.permission.READ_CALL_LOG,  //必选
                    Manifest.permission.WRITE_CALL_LOG,  //必选
                    Manifest.permission.PROCESS_OUTGOING_CALLS,  //必选
            };
        } else {
            NEED_PERMISSION = new String[]{
                    Manifest.permission.PROCESS_OUTGOING_CALLS,  //必选
            };
        }
    }


    @Override
    public boolean isCheckEnable(Context context, MissHelperConfiguration configuration) {
        MissHelper.getMissHelperConfiguration().getRomStrategy().isNeedCheck();


        return true;
    }

    @Override
    public String getPermissionName() {
        return "通话记录";
    }

    @Override
    public int getPermissionIconRes() {
        return R.drawable.miss_permission_ic_phone;
    }

    @Override
    public String[] getPermissions() {
        return NEED_PERMISSION;
    }
}
