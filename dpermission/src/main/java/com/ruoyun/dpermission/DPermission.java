package com.ruoyun.dpermission;

import android.app.Activity;
import android.support.v4.app.Fragment;
import com.ruoyun.dpermission.manufacturer.IRomStrategy;

/**
 * Created by ruoyun on 2017/8/4.
 * android  权限库
 */
public class DPermission {

    public static final int NEXT_STEP = 0x10;
    public static final int STOP_STEP = 0x15;
    public static final int WAIT_STEP = 0x20;
    private Builder builder;
    private final IRomStrategy iRomStrategy;

    private static class SingletonHolder {
        private static final DPermission INSTANCE = new DPermission();
    }

    private static DPermission getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private DPermission() {
        iRomStrategy = RomUtil.get();
    }

    public static Builder with(Activity activity) {
        DPermission dPermission = DPermission.getInstance();
        Builder builder = new Builder(activity, dPermission.iRomStrategy);
        dPermission.builder = builder;
        return builder;
    }

    public static Builder with(Fragment fragment) {
        return with(fragment.getActivity());
    }

    public static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (DPermission.getInstance().builder.request.getRequestCode() == requestCode) {
            DPermission.getInstance().builder.request.onRequestPermissionsResult(permissions, grantResults);
        }
    }

    public static class Builder {

        private PermissionRequest request;

        public Builder(Activity activity, IRomStrategy iRomStrategy) {
            request = new PermissionRequest(activity);
            request.setRomStrategy(iRomStrategy);
        }

        /**
         * 不传入也可以，有默认值
         *
         * @param requestCode
         * @return
         */
        public Builder setRequestCode(int requestCode) {
            request.setRequestCode(requestCode);
            return this;
        }

        public Builder addPermission(String permission) {
            request.addPermission(permission);
            return this;
        }

        public void checkPermission(PermissionRequest.PermissionListener listener) {
            request.start(listener);
        }
    }


}
