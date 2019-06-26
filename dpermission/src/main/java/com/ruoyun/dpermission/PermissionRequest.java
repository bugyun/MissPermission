package com.ruoyun.dpermission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import com.ruoyun.dpermission.manufacturer.IRomStrategy;

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
    private IRomStrategy iRomStrategy;
    private final WeakReference<Activity> activityWeakReference;
    private int requestCode = 999898;

    private boolean isOver23;//是不是棉花糖，大于：true  小于 false

    public void setRomStrategy(IRomStrategy iRomStrategy) {
        this.iRomStrategy = iRomStrategy;
    }

    public PermissionRequest(Activity activity) {
        activityWeakReference = new WeakReference<>(activity);
    }

    public void addPermission(String permission) {
        permissionList.add(permission);
    }

    public void start(PermissionListener permissionListener) {
        this.permissionListener = permissionListener;
        checkPermission();
    }

    public void next() {
        requestPermission();
    }

    private void checkPermission() {
        if (permissionList.isEmpty()) {
            permissionListener.onFailure(new PermissionException("请求权限为空"));
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            isOver23 = true;
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
            switch (permissionListener.onChecked(agreePermissionList, rejectPermissionList, this)) {
                case DPermission.NEXT_STEP:
                    next();
                    break;
                case DPermission.STOP_STEP:
                case DPermission.WAIT_STEP:
                default:
                    break;
            }
        } else {
            isOver23 = false;
            /*
             * 检测 23 版本以下 权限的方法 PermissionChecker.checkSelfPermission
             * PERMISSION_GRANTED: 已授权
             * PERMISSION_DENIED: 没有被授权
             * PERMISSION_DENIED_APP_OP: 没有被授权
             */
            for (String permission : permissionList) {
                if (activityWeakReference.get() == null) {
                    permissionListener.onFailure(new PermissionException("activity 为空"));
                    return;
                }
                int checkSelfPermission = PermissionChecker.checkSelfPermission(activityWeakReference.get(), permission);
                if (checkSelfPermission == PermissionChecker.PERMISSION_GRANTED) {//已授权
                    agreePermissionList.add(permission);
                } else {//未授权
                    rejectPermissionList.add(permission);
                }
            }
            if (permissionList.size() == agreePermissionList.size()) {
                permissionListener.onSuccess(); //所有权限都通过
            } else {
                permissionListener.onDenied(isOver23, rejectPermissionList, true, this);
            }
        }
    }

    private void requestPermission() {
        if (permissionList.size() == agreePermissionList.size()) {//判断集合
            //所有权限都通过
            permissionListener.onSuccess();
        } else {
            requestPermissionsAgain(rejectPermissionList);
        }
    }

    public void requestPermissionsAgain(List<String> permissionLists) {
        if (activityWeakReference.get() == null) {
            permissionListener.onFailure(new PermissionException("activity 为空"));
            return;
        }
//        ActivityCompat.requestPermissions(activityWeakReference.get(), permissionLists.toArray(new String[permissionLists.size()]), requestCode);
        ActivityCompat.requestPermissions(activityWeakReference.get(), permissionLists.toArray(new String[0]), requestCode);
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public interface PermissionListener {
        /**
         * @param agreeList
         * @param rejectList
         * @param request
         * @return NEXT_STEP：直接下一步，不用等待
         * STOP_STEP：直接停止，不执行下一步
         * PAUSE_STEP：等待，等待命令唤起下一步
         */
        int onChecked(List<String> agreeList, List<String> rejectList, PermissionRequest request);//检查结束

        /**
         * @param isOver23          是否大于Build.VERSION_CODES.M
         * @param deniedPermissions
         * @param alwaysDenied
         * @param request
         */
        void onDenied(boolean isOver23, List<String> deniedPermissions, boolean alwaysDenied, PermissionRequest request);

        void onSuccess();//权限完成

        void onFailure(PermissionException exception);//失败
    }


    public void onRequestPermissionsResult(String[] permissions, int[] grantResults) {
        boolean alwaysDenied = false;
        if (grantResults.length > 0) {
            List<String> deniedList = new ArrayList<>();
            //            List<String> agreeList = new ArrayList<>();
            // 遍历所有申请的权限，把被拒绝的权限放入集合
            for (int i = 0; i < grantResults.length; i++) {
                int grantResult = grantResults[i];
                if (grantResult != PackageManager.PERMISSION_GRANTED) {//没有授予权限
                    //如果用户在过去拒绝了权限请求，并在权限请求系统对话框中选择了 Don't ask again 选项，此方法将返回 false。如果设备规范禁止应用具有该权限，此方法也会返回 false。
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(activityWeakReference.get(), permissions[i])) {
                        alwaysDenied = true;
                    }
                    deniedList.add(permissions[i]);
                }
            }
            if (!deniedList.isEmpty()) {
                permissionListener.onDenied(isOver23, deniedList, alwaysDenied, this);
            } else {
                permissionListener.onSuccess();
            }
        }
    }
}
