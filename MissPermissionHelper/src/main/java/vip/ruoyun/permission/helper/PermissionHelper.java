package vip.ruoyun.permission.helper;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import java.util.List;

import vip.ruoyun.permission.core.MissPermission;
import vip.ruoyun.permission.core.PermissionException;
import vip.ruoyun.permission.core.PermissionRequest;
import vip.ruoyun.permission.helper.check.CameraChecker;

/**
 * Created by ruoyun on 2019-06-26.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class PermissionHelper {


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

    public interface DoAction {
        void doHandle(Context context);
    }


    public static void doOpenCamera(final Fragment fragment, final DoAction doAction) {
        doOpenCamera(fragment.getActivity(), doAction);
    }

    public static void doOpenCamera(final Activity activity, final DoAction doAction) {
        MissPermission.with(activity).addPermission(Manifest.permission.CAMERA).checkPermission(new PermissionRequest.PermissionListener() {


            @Override
            public int onChecked(List<String> agreeList, List<String> rejectList, PermissionRequest request) {

                //展示 ui





                return 0;
            }

            @Override
            public void onDenied(boolean isOver23, List<String> deniedPermissions, boolean alwaysDenied, PermissionRequest request) {
                if (isOver23) {//23以上


                } else {
                    DialogUtil.showPermissionManagerDialog(activity, "相机");
                }
            }

            @Override
            public void onSuccess() {
                if (CameraChecker.isCheckEnable(activity)) {
                    doAction.doHandle(activity);
                } else {
                    DialogUtil.showPermissionManagerDialog(activity, "相机");
                }
            }

            @Override
            public void onFailure(PermissionException exception) {
                DialogUtil.showPermissionManagerDialog(activity, "相机");
            }
        });
    }


}
