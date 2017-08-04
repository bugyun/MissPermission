package com.ruoyun.dpermission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanpu on 2017/8/4.
 * 权限请求
 */
public class PermissionRequest {

    private List<String> permissionList = new ArrayList<>();
    private PermissionListener permissionListener;
    private Activity activity;

    public PermissionListener getPermissionListener() {
        return permissionListener;
    }

    public void setPermissionListener(PermissionListener permissionListener) {
        this.permissionListener = permissionListener;
    }

    public void addPermission(String permission) {
        permissionList.add(permission);
    }

    public void start() {
        if (permissionListener.onPreHint()) {//显示提示框
            return;
        }
        requestPermission(activity, (String) permissionList.toArray()[0], hashCode());
    }


    private void requestPermission(Activity activity, String permission, int requestCode) {
        if (!isGranted(activity, permission)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                permissionListener.rationale();
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
            }
        } else {
            //直接执行相应操作了
            permissionListener.granted();
        }
    }

    public boolean isGranted(Context context, String permission) {
        return !isMarshmallow() || isGranted_(context, permission);
    }

    private boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    private boolean isGranted_(Context context, String permission) {
        int checkSelfPermission = ActivityCompat.checkSelfPermission(context, permission);
        return checkSelfPermission == PackageManager.PERMISSION_GRANTED;
    }

    public void addActivity(Activity activity) {
        this.activity = activity;
    }

    public interface PermissionListener {
        //            * @see #onPreExecute()
        //     * @see #onPostExecute
        //     * @see #publishProgress

        boolean onPreHint();//申请前的弹窗

        void rationale();//原理的阐述

        void granted();//权限完成

        void denied(List<String> deniedList);//权限失败，参数：失败的权限
    }


    public void onRequestPermissionsResult(String[] permissions, int[] grantResults) {
        if (grantResults.length > 0) {
            List<String> deniedList = new ArrayList<>();
            // 遍历所有申请的权限，把被拒绝的权限放入集合
            for (int i = 0; i < grantResults.length; i++) {
                int grantResult = grantResults[i];
                if (grantResult == PackageManager.PERMISSION_GRANTED) {
                    permissionListener.granted();
                } else {
                    deniedList.add(permissions[i]);
                }
            }
            if (!deniedList.isEmpty()) {
                permissionListener.denied(deniedList);
            }
        }
    }

    public static class WarpperPermissionListener implements PermissionRequest.PermissionListener {
        @Override
        public boolean onPreHint() {
            //检测手机的类型
            if (RomUtil.isNeedShowHint()) {

            }
            return true;
        }

        @Override
        public void rationale() {

        }

        @Override
        public void granted() {

        }

        @Override
        public void denied(List<String> deniedList) {

        }

    }


}
