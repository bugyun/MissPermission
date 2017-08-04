package com.ruoyun.dpermission;

import android.app.Activity;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by ruoyun on 2017/8/4.
 * android  权限库
 */
public class DPermission {

    private static class SingletonHolder {
        private static final DPermission INSTANCE = new DPermission();
    }

    public static DPermission getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private DPermission() {
    }

    public Builder with() {
        return new Builder();
    }

    private HashMap<Integer, PermissionRequest> requestList = new HashMap<>();

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestList.containsKey(requestCode)) {
            PermissionRequest request = requestList.get(requestCode);
            request.onRequestPermissionsResult(permissions, grantResults);
        } else {
            //失败的方法
            Log.e("zyh", "can not find request");
        }
    }

    private void requestPermission(PermissionRequest request) {
        requestList.put(request.hashCode(), request);
        request.start();
    }

    public void removePermission(int id) {
        if (requestList.containsKey(id)) {
            requestList.remove(id);
        }
    }

    public static class Builder {

        private PermissionRequest request;

        public Builder() {
            request = new PermissionRequest();
        }

        public Builder setPermissionListener(PermissionRequest.PermissionListener listener) {
            request.setPermissionListener(listener);
            return this;
        }

        public Builder addPermission(String permission) {
            request.addPermission(permission);
            return this;
        }

        public Builder addActivity(Activity activity) {
            request.addActivity(activity);
            return this;
        }

        public Builder create() {
            if (null == request.getPermissionListener()) {
                request.setPermissionListener(new PermissionRequest.WarpperPermissionListener());
            }
            return this;
        }

        public void start() {
            DPermission.getInstance().requestPermission(request);
        }

    }





}
