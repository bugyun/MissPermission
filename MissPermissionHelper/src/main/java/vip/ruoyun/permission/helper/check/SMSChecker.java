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
public class SMSChecker implements IChecker {

    private IRomStrategy iRomStrategy;

    public SMSChecker(IRomStrategy iRomStrategy) {
        this.iRomStrategy = iRomStrategy;
    }

    private final String[] NEED_PERMISSION = {
            Manifest.permission.SEND_SMS,//
            Manifest.permission.RECEIVE_SMS,//
            Manifest.permission.READ_SMS,//
            Manifest.permission.RECEIVE_WAP_PUSH,//
            Manifest.permission.RECEIVE_MMS,//
    };

    @Override
    public boolean isCheckEnable(Context context, MissHelperConfiguration configuration) {
        return false;
    }

    @Override
    public String getPermissionName() {
        return "信息";
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
