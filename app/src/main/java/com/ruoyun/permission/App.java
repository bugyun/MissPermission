package com.ruoyun.permission;

import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import java.lang.ref.WeakReference;

import vip.ruoyun.permission.pro.MissPermission;
import vip.ruoyun.screen.ScreenHelper;

public class App extends Application {

    @Override
    public Resources getResources() {
        return ScreenHelper.applyAdapt(super.getResources(), 375f, ScreenHelper.WIDTH_DP);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MissPermission.register(this);
//        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
//
////            MissPermission.
//
//            private WeakReference<Activity> activityWeakReference;
//
//            @Override
//            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
//                Log.e("ActivityLifecycle", "onActivityCreated" + activity.hashCode());
//
//            }
//
//            @Override
//            public void onActivityStarted(Activity activity) {
//                activityWeakReference = new WeakReference<Activity>(activity);
//                Log.e("ActivityLifecycle", "onActivityStarted" + activity.hashCode());
//            }
//
//            @Override
//            public void onActivityResumed(Activity activity) {
//                Log.e("ActivityLifecycle", "onActivityResumed" + activity.hashCode());
//            }
//
//            @Override
//            public void onActivityPaused(Activity activity) {
//                Log.e("ActivityLifecycle", "onActivityPaused" + activity.hashCode());
//                activityWeakReference.clear();
//            }
//
//            @Override
//            public void onActivityStopped(Activity activity) {
//                Log.e("ActivityLifecycle", "onActivityStopped" + activity.hashCode());
//
//            }
//
//            @Override
//            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
//                Log.e("ActivityLifecycle", "onActivitySaveInstanceState" + activity.hashCode());
//
//
//            }
//
//            @Override
//            public void onActivityDestroyed(Activity activity) {
//                Log.e("ActivityLifecycle", "onActivityDestroyed" + activity.hashCode());
//
//            }
//        });
    }
}
