package com.ruoyun.dpermission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanpu on 2017/8/4.
 * 权限请求
 */
public class PermissionRequest {

    private List<String> permissionList = new ArrayList<>();
    private List<String> agreePermissionList = new ArrayList<>();
    private List<String> rejectPermissionList = new ArrayList<>();
    private PermissionListener permissionListener;
    private WeakReference<Activity> activityWeakReference;
    private int requestCode;

    public PermissionListener getPermissionListener() {
        return permissionListener;
    }

    public void setPermissionListener(PermissionListener permissionListener) {
        this.permissionListener = permissionListener;
    }

    public void addPermission(String permission) {
        permissionList.add(permission);
    }

    public void start(Activity activity) {
        activityWeakReference = new WeakReference<Activity>(activity);
        checkPermission();
    }

    public void next() {
        requestPermission();
    }

    public void stop() {
        DPermission.getInstance().removePermission(requestCode);
    }

    private void checkPermission() {
        if (permissionList.isEmpty()) {
            permissionListener.onFailure(new PermissionException("请求权限为空"));
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissionList) {
                if (activityWeakReference.get() == null) {
                    permissionListener.onFailure(new PermissionException("activity 为空"));
                    return;
                }
                //ContextCompat
                int checkSelfPermission = ActivityCompat.checkSelfPermission(activityWeakReference.get(), permission);
                if (checkSelfPermission == PackageManager.PERMISSION_GRANTED) {//如果同意
                    agreePermissionList.add(permission);
                } else {//如果拒绝
                    rejectPermissionList.add(permission);
                }
            }
            switch (permissionListener.onChecked(true, agreePermissionList, rejectPermissionList, this)) {
                case DPermission.NEXT_STEP:
                    next();
                    break;
                case DPermission.STOP_STEP:
                    stop();
                    break;
                case DPermission.WAIT_STEP:
                    break;
                default:
                    stop();
                    break;
            }
        } else {
            permissionListener.onChecked(false, agreePermissionList, rejectPermissionList, this);
            stop();
        }
    }

    private void requestPermission() {
        //判断集合
        if (permissionList.size() == agreePermissionList.size()) {
            //所有权限都通过
            permissionListener.onSuccess();
        } else {
            //            List<String> shouldShowRequestPermissionRationaleList = new ArrayList<>();
            //            if (activityWeakReference.get() == null) {
            //                permissionListener.onFailure(new PermissionException("activity 为空"));
            //                return;
            //            }
            //            for (String permission : rejectPermissionList) {
            //                if (ActivityCompat.shouldShowRequestPermissionRationale(activityWeakReference.get(), permission)) {
            //                    shouldShowRequestPermissionRationaleList.add(permission);
            //                }
            //            }
            //            if (shouldShowRequestPermissionRationaleList.size() > 0) {
            //                permissionListener.onRationale(shouldShowRequestPermissionRationaleList);//弹出提示框
            //            } else {
            requestPermissionsAgain(rejectPermissionList);
            //            }
        }
    }


    public void requestPermissionsAgain(List<String> permissionLists) {
        if (activityWeakReference.get() == null) {
            permissionListener.onFailure(new PermissionException("activity 为空"));
            return;
        }
        ActivityCompat.requestPermissions(activityWeakReference.get(), permissionLists.toArray(new String[permissionLists.size()]), requestCode);
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public interface PermissionListener {
        /**
         * @param isGreater  是否大于Build.VERSION_CODES.M
         * @param agreeList
         * @param rejectList
         * @param request
         * @return NEXT_STEP：直接下一步，不用等待
         * STOP_STEP：直接停止，不执行下一步
         * PAUSE_STEP：等待，等待命令唤起下一步
         */
        int onChecked(boolean isGreater, List<String> agreeList, List<String> rejectList, PermissionRequest request);//检查结束

        /**
         * @param deniedPermissions
         * @param alwaysDenied
         * @param request
         */
        void onDenied(List<String> deniedPermissions, boolean alwaysDenied, PermissionRequest request);

        void onSuccess();//权限完成

        void onFailure(PermissionException exception);//失败
    }


    public void onRequestPermissionsResult(String[] permissions, int[] grantResults) {
        boolean isShowRequest = true;
        if (grantResults.length > 0) {
            List<String> deniedList = new ArrayList<>();
            //            List<String> agreeList = new ArrayList<>();
            // 遍历所有申请的权限，把被拒绝的权限放入集合

            for (int i = 0; i < grantResults.length; i++) {
                int grantResult = grantResults[i];
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(activityWeakReference.get(), permissions[i])) {
                        isShowRequest = false;
                    }
                    deniedList.add(permissions[i]);
                }
            }
            if (!deniedList.isEmpty()) {
                permissionListener.onDenied(deniedList, isShowRequest, this);
            } else {
                permissionListener.onSuccess();
                stop();
            }
        }
    }

    public static class WrapperPermissionListener implements PermissionRequest.PermissionListener {

        @Override
        public int onChecked(boolean isGreater, List<String> agreeList, List<String> rejectList, PermissionRequest request) {
            //检测手机的类型
            if (RomUtil.isNeedShowHint()) {

            }
            //            request.next();
            return DPermission.NEXT_STEP;
        }

        @Override
        public void onDenied(List<String> deniedPermissions, boolean alwaysDenied, PermissionRequest request) {

        }

        @Override
        public void onSuccess() {

        }


        @Override
        public void onFailure(PermissionException exception) {

        }
    }

}
