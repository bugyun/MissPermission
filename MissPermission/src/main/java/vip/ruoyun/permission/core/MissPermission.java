package vip.ruoyun.permission.core;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by ruoyun on 2019-06-27.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class MissPermission {

    public static final int NEXT_STEP = 0x10;
    public static final int STOP_STEP = 0x15;
    public static final int WAIT_STEP = 0x20;
    private Builder builder;

    private static class SingletonHolder {
        private static final MissPermission INSTANCE = new MissPermission();
    }

    private static MissPermission getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private MissPermission() {

    }

    public static Builder with(Activity activity) {
        Builder builder = new Builder(activity);
        getInstance().builder = builder;
        return builder;
    }

    public static Builder with(Fragment fragment) {
        return with(fragment.getActivity());
    }

    public static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (MissPermission.getInstance().builder.request.getRequestCode() == requestCode) {
            MissPermission.getInstance().builder.request.onRequestPermissionsResult(permissions, grantResults);
        }
    }

    public static class Builder {

        private PermissionRequest request;

        Builder(Activity activity) {
            request = new PermissionRequest(activity);
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
