package com.ruoyun.permission.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by ruoyun on 2019-07-16.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class AvoidOnResultManager {
    private static final String TAG = "RYAvoidOnResultManager";

    private AvoidOnResultManager() {
    }

    private static AvoidOnResultFragment getAvoidOnResultFragment(FragmentActivity activity) {
        FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
        AvoidOnResultFragment avoidOnResultFragment = (AvoidOnResultFragment) supportFragmentManager.findFragmentByTag(TAG);
        if (avoidOnResultFragment == null) {
            avoidOnResultFragment = new AvoidOnResultFragment();
            supportFragmentManager.beginTransaction()
                    .add(avoidOnResultFragment, TAG)
                    .commitNowAllowingStateLoss();
        }
        return avoidOnResultFragment;
    }


    private static void test(Activity activity) {
        if (activity instanceof FragmentActivity) {
            FragmentManager supportFragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
            AvoidOnResultFragment avoidOnResultFragment = (AvoidOnResultFragment) supportFragmentManager.findFragmentByTag(TAG);
            if (avoidOnResultFragment == null) {
                avoidOnResultFragment = new AvoidOnResultFragment();
                supportFragmentManager.beginTransaction()
                        .add(avoidOnResultFragment, TAG)
                        .commitNowAllowingStateLoss();
            }
        } else {
//            android.app.FragmentManager fragmentManager = activity.getFragmentManager();
//            PermissionFragment permissionFragment = (PermissionFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
//            if (null == permissionFragment) {
//                permissionFragment = new PermissionFragment();
//                fragmentManager.beginTransaction()
//                        .add(permissionFragment, TAG)
//                        .commitAllowingStateLoss();
//                fragmentManager.executePendingTransactions();
//            }
        }
    }

    /**
     * 打开 activity
     *
     * @param activity
     * @param intent
     * @param callback
     */
    public static void startActivityForResult(FragmentActivity activity, Intent intent, ActivityCallback callback) {
        startActivityForResult(activity, intent, null, callback);
    }

    public static void startActivityForResult(FragmentActivity activity, Intent intent, Bundle options, ActivityCallback callback) {
        AvoidOnResultFragment avoidOnResultFragment = getAvoidOnResultFragment(activity);
        avoidOnResultFragment.startActivityForResult(intent, options, callback);
    }

    public interface ActivityCallback {
        void onActivityResult(int resultCode, Intent data);
    }

    /**
     * 请求权限
     *
     * @param activity
     * @param permissions
     * @param permissionsCallBack
     */
    public static void requestPermissions(FragmentActivity activity, String[] permissions, PermissionsCallBack permissionsCallBack) {
        AvoidOnResultFragment avoidOnResultFragment = getAvoidOnResultFragment(activity);
        avoidOnResultFragment.requestPermissions(permissions, permissionsCallBack);
    }

    public interface PermissionsCallBack {
        void onRequestPermissionsResult(@NonNull String[] permissions, @NonNull int[] grantResults);
    }


}
