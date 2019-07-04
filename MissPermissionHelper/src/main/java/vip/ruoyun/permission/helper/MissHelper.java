package vip.ruoyun.permission.helper;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;

import java.util.Arrays;
import java.util.List;

import vip.ruoyun.permission.core.MissPermission;
import vip.ruoyun.permission.core.PermissionException;
import vip.ruoyun.permission.core.PermissionRequest;
import vip.ruoyun.permission.helper.check.CameraChecker;
import vip.ruoyun.permission.helper.core.MissHelperConfiguration;

public class MissHelper {

    private MissHelper() {
    }

    private static MissHelperConfiguration missHelperConfiguration;

    private static void init(MissHelperConfiguration configuration) {
        missHelperConfiguration = configuration;
    }

    /**
     * 简单有没有权限,如果 [] 为空或者 为 null 时，返回有权限
     *
     * @param context
     * @param permissions
     * @return
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
                if (PermissionChecker.checkSelfPermission(context, permission) != PermissionChecker.PERMISSION_GRANTED) {
                    isHasPermission = false;
                    break;
                }
            }
        }
        return isHasPermission;
    }


    public static void doOpenCamera(final Fragment fragment, final DoActionWrapper doAction) {
        doOpenCamera(fragment.getActivity(), doAction);
    }

    public static void doOpenCamera(final Activity activity, final DoActionWrapper doAction) {
        MissPermission.with(activity)
                .addPermission(Manifest.permission.CAMERA)
                .checkPermission(new PermissionRequest.PermissionListener() {
                    @Override
                    public int onChecked(List<String> agreePermissions, List<String> deniedPermissions, PermissionRequest request) {
                        missHelperConfiguration.getAction().checkedAction(request.getContext(), deniedPermissions, request);
                        return MissPermission.WAIT_STEP;
                    }

                    @Override
                    public void onDenied(List<String> deniedPermissions, boolean alwaysDenied, PermissionRequest request) {
                        missHelperConfiguration.getAction().deniedAction(request.getContext(), deniedPermissions, alwaysDenied, request);
                        doAction.onFailure(request.getContext());
                    }

                    @Override
                    public void onSuccess(PermissionRequest request) {
                        if (CameraChecker.isCheckEnable(activity, missHelperConfiguration)) {
                            doAction.onSuccess(activity);
                        } else {
                            missHelperConfiguration.getAction().deniedAction(activity, Arrays.asList(CameraChecker.NEED_PERMISSION), true, request);
                        }
                    }

                    @Override
                    public void onFailure(PermissionException exception) {
                        doAction.onFailure(activity);
                    }
                });
    }


    public interface DoAction {
        void onSuccess(Context context);

        void onFailure(Context context);
    }

    public abstract static class DoActionWrapper implements DoAction {

        @Override
        public void onFailure(Context context) {

        }
    }
}
