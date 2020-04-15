package vip.ruoyun.permission.pro;

import android.app.Activity;
import android.app.Application;
import android.app.ApplicationErrorReport;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.PermissionChecker;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;

import vip.ruoyun.permission.pro.check.PermissionsChecker;

public class MissPermission {

    private static WeakReference<FragmentActivity> activityWeakReference;

    public static void register(Application app) {
        app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (activity instanceof FragmentActivity) {
                    activityWeakReference = new WeakReference<FragmentActivity>((FragmentActivity) activity);
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
//                activityWeakReference.clear();
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        });

    }

    private MissPermission() {
    }

    public static Builder with(FragmentActivity activity) {
        return new Builder(activity);
    }

    public static Builder with(Fragment fragment) {
        return with(fragment.getActivity());
    }

    public static Builder with() {
        return new Builder(activityWeakReference);
    }

    public static class Builder {

        private PermissionRequest request;

        private Builder(FragmentActivity activity) {
            request = new PermissionRequest(activity);
        }

        private Builder(WeakReference<FragmentActivity> activityWeakReference) {
            request = new PermissionRequest(activityWeakReference);
        }

        /**
         * 添加权限
         */
        public Builder permission(String permission) {
            request.addPermission(permission);
            return this;
        }

        /**
         * 添加权限
         */
        public Builder permissions(List<String> permissions) {
            request.addPermissions(permissions);
            return this;
        }

        /**
         * 添加权限
         */
        public Builder permissions(String[] permissions) {
            request.addPermissions(Arrays.asList(permissions));
            return this;
        }

        /**
         * 设置请求回调，并开始请求权限
         */
        public void check(PermissionRequest.PermissionListener listener) {
            //检查
            if (null == request.getAction()) {
                request.setAction(new DefaultAction());
            }
            request.start(listener);
        }

        /**
         * 是否显示弹框
         */
        public Builder prompt(boolean showPrompt) {
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
        public Builder style(@StyleRes int styleResId) {
            request.setStyleResId(styleResId);
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

    /**
     * 真实权限的检查,使用这个权限 来检测这个权限是否可用
     */
    public static boolean realCheck(Context context, String permission) {
        return PermissionsChecker.isPermissionGranted(context, permission);
    }

}
