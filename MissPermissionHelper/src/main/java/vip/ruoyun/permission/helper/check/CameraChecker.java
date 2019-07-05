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

    public static final String[] FM_PERMISSION = {
            Manifest.permission.CAMERA,  //必选
    };


    public void testIntent() {
//        MediaStore.ACTION_IMAGE_CAPTURE
//        MediaStore.ACTION_VIDEO_CAPTURE
    }

    public static boolean isCheckEnable(Context context, MissHelperConfiguration configuration) {
        if (configuration.getRomStrategy().isNeedCheck()) {
            //检查


            return true;
        }
        return true;
    }
}




