package vip.ruoyun.permission.helper;

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
import vip.ruoyun.permission.helper.check.CalendarChecker;
import vip.ruoyun.permission.helper.check.CallLogChecker;
import vip.ruoyun.permission.helper.check.CameraChecker;
import vip.ruoyun.permission.helper.check.ContactsChecker;
import vip.ruoyun.permission.helper.check.LocationChecker;
import vip.ruoyun.permission.helper.check.MicrophoneChecker;
import vip.ruoyun.permission.helper.check.PhoneCheck;
import vip.ruoyun.permission.helper.check.SMSChecker;
import vip.ruoyun.permission.helper.check.SensorsChecker;
import vip.ruoyun.permission.helper.check.StorageChecker;
import vip.ruoyun.permission.helper.core.IChecker;
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


    public static void checkCalendar(final Activity activity, final DoActionWrapper doAction) {
        checkNotNullConfiguration();
        checkPermission(activity, doAction, new CalendarChecker(missHelperConfiguration.getRomStrategy()));
    }

    public static void checkCallLog(final Activity activity, final DoActionWrapper doAction) {
        checkNotNullConfiguration();
        checkPermission(activity, doAction, new CallLogChecker(missHelperConfiguration.getRomStrategy()));
    }

    public static void checkCamera(final Fragment fragment, final DoActionWrapper doAction) {
        checkCamera(fragment.getActivity(), doAction);
    }

    public static void checkCamera(final Activity activity, final DoActionWrapper doAction) {
        checkNotNullConfiguration();
        checkPermission(activity, doAction, new CameraChecker(missHelperConfiguration.getRomStrategy()));
    }

    public static void checkContacts(final Activity activity, final DoActionWrapper doAction) {
        checkNotNullConfiguration();
        checkPermission(activity, doAction, new ContactsChecker(missHelperConfiguration.getRomStrategy()));
    }

    public static void checkLocation(final Activity activity, final DoActionWrapper doAction) {
        checkNotNullConfiguration();
        checkPermission(activity, doAction, new LocationChecker(missHelperConfiguration.getRomStrategy()));
    }


    public static void checkMicrophone(final Activity activity, final DoActionWrapper doAction) {
        checkNotNullConfiguration();
        checkPermission(activity, doAction, new MicrophoneChecker(missHelperConfiguration.getRomStrategy()));
    }

    public static void checkPhone(final Activity activity, final DoActionWrapper doAction) {
        checkNotNullConfiguration();
        checkPermission(activity, doAction, new PhoneCheck(missHelperConfiguration.getRomStrategy()));
    }

    public static void checkSensors(final Activity activity, final DoActionWrapper doAction) {
        checkNotNullConfiguration();
        checkPermission(activity, doAction, new SensorsChecker(missHelperConfiguration.getRomStrategy()));
    }

    public static void checkSms(final Fragment fragment, final DoActionWrapper doAction) {
        checkSms(fragment.getActivity(), doAction);

    }

    public static void checkSms(final Activity activity, final DoActionWrapper doAction) {
        checkNotNullConfiguration();
        checkPermission(activity, doAction, new SMSChecker(missHelperConfiguration.getRomStrategy()));
    }

    public static void checkStorage(final Activity activity, final DoActionWrapper doAction) {
        checkNotNullConfiguration();
        checkPermission(activity, doAction, new StorageChecker(missHelperConfiguration.getRomStrategy()));
    }

    private static void checkPermission(final Activity activity, final DoActionWrapper doAction, final IChecker iChecker) {
        MissPermission.with(activity)
                .addPermissions(iChecker.getPermissions())
                .checkPermission(new PermissionRequest.PermissionListener() {
                    @Override
                    public int onChecked(List<String> agreePermissions, List<String> deniedPermissions, PermissionRequest request) {
                        missHelperConfiguration.getAction().checkedAction(request.getContext(), deniedPermissions, request, iChecker);
                        return MissPermission.WAIT_STEP;
                    }

                    @Override
                    public void onDenied(List<String> deniedPermissions, boolean alwaysDenied, PermissionRequest request) {
                        missHelperConfiguration.getAction().deniedAction(request.getContext(), deniedPermissions, alwaysDenied, request);
                        doAction.onFailure(request.getContext());
                    }

                    @Override
                    public void onSuccess(PermissionRequest request) {
                        if (iChecker.isCheckEnable(activity, missHelperConfiguration)) {
                            doAction.onSuccess(activity);
                        } else {
                            missHelperConfiguration.getAction().deniedAction(activity, Arrays.asList(iChecker.getPermissions()), true, request);
                        }
                    }

                    @Override
                    public void onFailure(PermissionException exception) {
                        doAction.onFailure(activity);
                    }
                });
    }


    private static void checkNotNullConfiguration() {
        if (missHelperConfiguration == null) {
            missHelperConfiguration = new MissHelperConfiguration.Builder().build();
        }
    }

    public static void checkPermissions(final Activity activity, final DoActionWrapper doAction, List<String> permissions) {
        checkNotNullConfiguration();
        MissPermission.with(activity)
                .addPermissions(permissions)
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
                        doAction.onSuccess(activity);
                    }

                    @Override
                    public void onFailure(PermissionException exception) {
                        doAction.onFailure(activity);
                    }
                });
    }

    public static void checkPermissions(final Activity activity, final DoActionWrapper doAction, String[] permissions) {
        checkNotNullConfiguration();
        MissPermission.with(activity)
                .addPermissions(permissions)
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
                        doAction.onSuccess(activity);
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
