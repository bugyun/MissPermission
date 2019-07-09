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
public class PhoneCheck implements IChecker {

    public static final String PERMISSION_NAME = "日历";

    static final int PERMISSION_ICONRES = R.drawable.miss_permission_ic_calendar;

    private IRomStrategy iRomStrategy;
    //    CallLogChecker
    private final String[] NEED_PERMISSION;

    public PhoneCheck(IRomStrategy iRomStrategy) {
        this.iRomStrategy = iRomStrategy;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NEED_PERMISSION = new String[]{
                    Manifest.permission.READ_PHONE_STATE,//
                    Manifest.permission.READ_PHONE_NUMBERS,//
                    Manifest.permission.CALL_PHONE,//
                    Manifest.permission.CALL_PHONE,//
                    Manifest.permission.ANSWER_PHONE_CALLS,//
                    Manifest.permission.WRITE_CALL_LOG,//
                    Manifest.permission.ADD_VOICEMAIL,//
                    Manifest.permission.USE_SIP,//
                    Manifest.permission.PROCESS_OUTGOING_CALLS,//
            };

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            NEED_PERMISSION = new String[]{
                    Manifest.permission.READ_PHONE_STATE,//
                    Manifest.permission.CALL_PHONE,//
                    Manifest.permission.CALL_PHONE,//
                    Manifest.permission.WRITE_CALL_LOG,//
                    Manifest.permission.ADD_VOICEMAIL,//
                    Manifest.permission.USE_SIP,//
                    Manifest.permission.PROCESS_OUTGOING_CALLS,//
            };
        } else {
            NEED_PERMISSION = new String[]{
                    Manifest.permission.READ_PHONE_STATE,//
                    Manifest.permission.CALL_PHONE,//
                    Manifest.permission.CALL_PHONE,//
                    Manifest.permission.ADD_VOICEMAIL,//
                    Manifest.permission.USE_SIP,//
                    Manifest.permission.PROCESS_OUTGOING_CALLS,//
            };
        }

    }

    @Override
    public boolean isCheckEnable(Context context, MissHelperConfiguration configuration) {
        return false;
    }

    @Override
    public String getPermissionName() {
        return "设备信息";
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




