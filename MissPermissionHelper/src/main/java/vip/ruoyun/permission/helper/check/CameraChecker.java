package vip.ruoyun.permission.helper.check;

import android.Manifest;
import android.content.Context;

import vip.ruoyun.permission.helper.R;
import vip.ruoyun.permission.helper.core.IChecker;
import vip.ruoyun.permission.helper.core.IRomStrategy;
import vip.ruoyun.permission.helper.core.MissHelperConfiguration;

/**
 * Created by ruoyun on 2019-06-26.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class CameraChecker implements IChecker {
    public static final String PERMISSION_NAME = "日历";

    static final int PERMISSION_ICONRES = R.drawable.miss_permission_ic_calendar;

    private IRomStrategy iRomStrategy;

    public CameraChecker(IRomStrategy iRomStrategy) {
        this.iRomStrategy = iRomStrategy;
    }

    private String[] NEED_PERMISSION = {
            Manifest.permission.CAMERA,  //必选
    };


    public void testIntent() {
//        MediaStore.ACTION_IMAGE_CAPTURE
//        MediaStore.ACTION_VIDEO_CAPTURE
    }

    public boolean isCheckEnable(Context context, MissHelperConfiguration configuration) {
        if (configuration.getRomStrategy().isNeedCheck()) {
            //检查


            return true;
        }
        return true;
    }

    @Override
    public String getPermissionName() {
        return "相机";
    }

    @Override
    public int getPermissionIconRes() {
        return R.drawable.miss_permission_ic_camera;
    }

    @Override
    public String[] getPermissions() {
        return NEED_PERMISSION;
    }
}




