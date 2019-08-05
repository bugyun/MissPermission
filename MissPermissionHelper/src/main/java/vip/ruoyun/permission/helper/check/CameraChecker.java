package vip.ruoyun.permission.helper.check;

import android.Manifest;

import vip.ruoyun.permission.helper.R;

/**
 * Created by ruoyun on 2019-06-26.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class CameraChecker {
    public static final String PERMISSION_NAME = "相机";

    public static final int PERMISSION_ICON_RES = R.drawable.miss_permission_ic_camera;

    private String[] NEED_PERMISSION = {
            Manifest.permission.CAMERA,  //必选
    };

}




