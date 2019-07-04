package vip.ruoyun.permission.helper.check;

import android.Manifest;
import android.content.Context;

import vip.ruoyun.permission.helper.core.MissHelperConfiguration;

/**
 * Created by ruoyun on 2019-06-26.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class CameraChecker {


    public static final String[] NEED_PERMISSION = {//
            //定位
            Manifest.permission.ACCESS_COARSE_LOCATION,//
            Manifest.permission.ACCESS_FINE_LOCATION,//
            //联系人
            Manifest.permission.READ_CONTACTS,//
            //照相机
            Manifest.permission.CAMERA,//
    };


    private static final String[] FM_PERMISSION = {
            Manifest.permission.ACCESS_COARSE_LOCATION,  //必选
            Manifest.permission.READ_PHONE_STATE,  //必选
    };

    private static final String[] FACE_PERMISSION = {
            Manifest.permission.READ_EXTERNAL_STORAGE,  //必选
            Manifest.permission.WRITE_EXTERNAL_STORAGE,  //必选
    };


    public static boolean isCheckEnable(Context context, MissHelperConfiguration configuration) {
        if (configuration.getRomStrategy().isNeedCheck()) {
            //检查


            return true;
        }
        return true;
    }
}




