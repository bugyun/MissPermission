package vip.ruoyun.permission.helper;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.PermissionChecker;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import vip.ruoyun.helper.avoid.AvoidOnResultHelper;

/**
 * Created by fanpu on 2017/8/4.
 * 权限请求
 */
public class PermissionRequest implements AvoidOnResultHelper.PermissionsCallBack {

    //
    private Set<String> permissionList = new HashSet<>();//请求的权限
    private Set<String> agreePermissionList = new HashSet<>();//同意的权限
    private Set<String> deniedPermissionList = new HashSet<>();//拒绝的权限
    private PermissionException exception;
    private Set<PermissionGroup> permissionGroups = new HashSet<>();

    private boolean alwaysDenied = false;//是否总是拒绝
    private boolean isOver23;//是不是棉花糖，大于：true  小于 false Build.VERSION_CODES.M

    //
    private PermissionListener permissionListener;
    private final WeakReference<FragmentActivity> activityWeakReference;

    //ui
    private boolean showprompt;
    private String title;
    private String msg;
    private int mFilterColor = 0;
    private int mStyleResId = -1;
    private boolean ischeck;
    private IAction baseAction = new DefaultAction();

    PermissionRequest(FragmentActivity activity) {
        activityWeakReference = new WeakReference<>(activity);
    }

    //开始
    void start(PermissionListener permissionListener) {
        this.permissionListener = permissionListener;
        checkPermission();
    }

    public void next() {
        requestPermission();
    }

    private void checkPermission() {
        if (permissionList.isEmpty()) {
            exception = new PermissionException("请求权限为空");
            permissionListener.onFailure(this);
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            isOver23 = true;
            for (String permission : permissionList) {
                if (activityWeakReference.get() == null) {
                    exception = new PermissionException("activity 为空");
                    permissionListener.onFailure(this);
                    return;
                }
                //ContextCompat
                int checkSelfPermission = ActivityCompat.checkSelfPermission(activityWeakReference.get(), permission);
                if (checkSelfPermission == PackageManager.PERMISSION_GRANTED) {//如果同意
                    agreePermissionList.add(permission);
                } else {//如果拒绝
                    deniedPermissionList.add(permission);
                    PermissionGroup permissionGroup = PermissionGroup.permissionGroupHashMap.get(permission);
                    if (permissionGroup != null) {
                        permissionGroups.add(permissionGroup);
                    }
                }
            }
            //展示提示框,要请求的权限
            if (showprompt) {
                //判断权限组
                baseAction.checkedAction(this, permissionGroups);
            } else {
                next();
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
                    exception = new PermissionException("activity 为空");
                    permissionListener.onFailure(this);
                    return;
                }
                int checkSelfPermission = PermissionChecker.checkSelfPermission(activityWeakReference.get(), permission);
                if (checkSelfPermission == PermissionChecker.PERMISSION_GRANTED) {//已授权
                    agreePermissionList.add(permission);
                } else {//未授权
                    deniedPermissionList.add(permission);
                }
            }
            if (permissionList.size() == agreePermissionList.size()) {
                permissionListener.onSuccess(this); //所有权限都通过
            } else {
                baseAction.deniedAction(this);
                permissionListener.onFailure(this);
            }
        }
    }

    private void requestPermission() {
        if (permissionList.size() == agreePermissionList.size()) {//判断集合
            //所有权限都通过
            permissionListener.onSuccess(this);
        } else {
            requestPermissionsAgain();
        }
    }

    public void requestPermissionsAgain() {
        if (activityWeakReference.get() == null) {
            exception = new PermissionException("activity 为空");
            permissionListener.onFailure(this);
            return;
        }
        AvoidOnResultHelper.requestPermissions(activityWeakReference.get(), deniedPermissionList.toArray(new String[0]), this);
    }

    @Override
    public void onRequestPermissionsResult(@NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0) {
            Set<String> deniedList = new HashSet<>();
            // 遍历所有申请的权限，把被拒绝的权限放入集合
            for (int i = 0; i < grantResults.length; i++) {
                int grantResult = grantResults[i];
                if (grantResult != PackageManager.PERMISSION_GRANTED) {//没有授予权限
                    //如果用户在过去拒绝了权限请求，并在权限请求系统对话框中选择了 Don't ask again 选项，此方法将返回 false。如果设备规范禁止应用具有该权限，此方法也会返回 false。
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(activityWeakReference.get(), permissions[i])) {
                        alwaysDenied = true;
                    }
                    deniedList.add(permissions[i]);
                } else {
                    agreePermissionList.add(permissions[i]);
                }
            }
            deniedPermissionList = deniedList;
            if (!deniedList.isEmpty()) {
                baseAction.deniedAction(this);
                permissionListener.onFailure(this);
            } else {
                permissionListener.onSuccess(this);
            }
        }
    }


    public interface PermissionListener {
        /**
         * @param request
         */
        void onSuccess(PermissionRequest request);//权限完成

        void onFailure(PermissionRequest request);//失败
    }

    //build 方法 get set

    void addPermission(String permission) {
        permissionList.add(permission);
    }

    void addPermissions(List<String> permissions) {
        permissionList.addAll(permissions);
    }

    //对外的 get set
    public boolean isOver23() {
        return isOver23;
    }

    public FragmentActivity getContext() {
        return activityWeakReference.get();
    }

    public Set<String> getPermissionList() {
        return permissionList;
    }

    public boolean isAlwaysDenied() {
        return alwaysDenied;
    }

    public Set<String> getAgreePermissionList() {
        return agreePermissionList;
    }

    public Set<String> getDeniedPermissionList() {
        return deniedPermissionList;
    }

    public PermissionException getException() {
        return exception;
    }
}
