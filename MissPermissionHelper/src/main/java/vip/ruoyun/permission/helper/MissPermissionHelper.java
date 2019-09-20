package vip.ruoyun.permission.helper;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.StyleRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.PermissionChecker;

import java.util.Arrays;
import java.util.List;

public class MissPermissionHelper {

    private static class SingletonHolder {

        private static final MissPermissionHelper INSTANCE = new MissPermissionHelper();
    }

    private static MissPermissionHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private MissPermissionHelper() {
    }

    public static Builder with(FragmentActivity activity) {
        return new Builder(activity);
    }

    public static Builder with(Fragment fragment) {
        return with(fragment.getActivity());
    }

    public static class Builder {

        private PermissionRequest request;

        private Builder(FragmentActivity activity) {
            request = new PermissionRequest(activity);
        }

        /**
         * 添加权限
         */
        public Builder addPermission(String permission) {
            request.addPermission(permission);
            return this;
        }

        /**
         * 添加权限
         */
        public Builder addPermissions(List<String> permissions) {
            request.addPermissions(permissions);
            return this;
        }

        /**
         * 添加权限
         */
        public Builder addPermissions(String[] permissions) {
            request.addPermissions(Arrays.asList(permissions));
            return this;
        }

        /**
         * 设置请求回调，并开始请求权限
         */
        public void checkPermission(PermissionRequest.PermissionListener listener) {
            //检查
            if (null == request.getAction()) {
                request.setAction(new DefaultAction());
            }
            request.start(listener);
        }

        /**
         * 是否显示弹框
         */
        public Builder showPrompt(boolean showPrompt) {
            request.setShowPrompt(showPrompt);
            return this;
        }

        /**
         * 设置弹框的标题
         */
        public Builder title(String title) {
            request.setTitle(title);
            return this;
        }

        /**
         * 设置弹框的描述
         */
        public Builder msg(String msg) {
            request.setMsg(msg);
            return this;
        }

        /**
         * 设置显示 ui
         */
        public Builder styleResId(@StyleRes int styleResId) {
            request.setStyleResId(styleResId);
            return this;
        }

        /**
         * 保留属性，是否需要检测
         */
        public Builder isCheck(boolean isCheck) {
            request.setIsCheck(isCheck);
            return this;
        }

        public Builder action(IAction iAction) {
            request.setAction(iAction);
            return this;
        }
    }

    /**
     * 简单有没有权限,如果 [] 为空或者 为 null 时，返回有权限
     */
    public static boolean check(Context context, String[] permissions) {
        boolean isHasPermission = true;//检测权限
        if (permissions == null) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    isHasPermission = false;
                    break;
                }
            }
        } else {
            for (String permission : permissions) {
                if (PermissionChecker.checkSelfPermission(context, permission)
                        != PermissionChecker.PERMISSION_GRANTED) {
                    isHasPermission = false;
                    break;
                }
            }
        }
        return isHasPermission;
    }
}
