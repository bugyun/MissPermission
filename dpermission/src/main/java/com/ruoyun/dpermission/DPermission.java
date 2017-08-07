package com.ruoyun.dpermission;

import android.app.Activity;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by ruoyun on 2017/8/4.
 * android  权限库
 */
public class DPermission {

    public static final int PERMISSIONS_REQUEST_CODE = 2000;
    public static final int NEXT_STEP = 0x10;
    public static final int STOP_STEP = 0x15;
    public static final int WAIT_STEP = 0x20;

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

    private void requestPermission(PermissionRequest request, Activity activity) {
        Log.e("zyh", "hashcode:" + request.hashCode());
        requestList.put(PERMISSIONS_REQUEST_CODE, request);
        request.start(activity);
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

        public Builder create() {
            if (null == request.getPermissionListener()) {
                request.setPermissionListener(new PermissionRequest.WrapperPermissionListener());
            }
            return this;
        }

        public void start(Activity activity) {
            DPermission.getInstance().requestPermission(request, activity);
        }

    }


}
