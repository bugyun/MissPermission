package vip.ruoyun.permission.helper.check;

import android.Manifest;
import android.content.Context;

import vip.ruoyun.permission.helper.core.IChecker;
import vip.ruoyun.permission.helper.core.IRomStrategy;
import vip.ruoyun.permission.helper.core.MissHelperConfiguration;

/**
 * Created by ruoyun on 2019-07-05.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class MicrophoneChecker implements IChecker {


    private IRomStrategy iRomStrategy;

    public MicrophoneChecker(IRomStrategy iRomStrategy) {
        this.iRomStrategy = iRomStrategy;
    }

    private final String[] NEED_PERMISSION = {
            Manifest.permission.RECORD_AUDIO,//
    };

    @Override
    public boolean isCheckEnable(Context context, MissHelperConfiguration configuration) {
        return false;
    }

    @Override
    public String getPermissionName() {
        return "录音";
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
