package vip.ruoyun.permission.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;
import android.util.Log;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

public class MissPermissionHelper {

    private MissPermissionHelper() {
    }

    private static MissHelperConfiguration missHelperConfiguration;

    private static void init(MissHelperConfiguration configuration) {
        missHelperConfiguration = configuration;
    }

    public static MissHelperConfiguration getMissHelperConfiguration() {
        return missHelperConfiguration;
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
        checkPermission(activity, doAction, new CalendarChecker());
    }

    public static void checkCallLog(final Activity activity, final DoActionWrapper doAction) {
        checkNotNullConfiguration();
        checkPermission(activity, doAction, new CallLogChecker());
    }

    public static void checkCamera(final Fragment fragment, final DoActionWrapper doAction) {
        checkCamera(fragment.getActivity(), doAction);
    }

    public static void checkCamera(final Activity activity, final DoActionWrapper doAction) {
        checkNotNullConfiguration();
        checkPermission(activity, doAction, new CameraChecker());
    }

    public static void checkContacts(final Activity activity, final DoActionWrapper doAction) {
        checkNotNullConfiguration();
        checkPermission(activity, doAction, new ContactsChecker());
    }

    public static void checkLocation(final Activity activity, final DoActionWrapper doAction) {
        checkNotNullConfiguration();
        checkPermission(activity, doAction, new LocationChecker());
    }


    public static void checkMicrophone(final Activity activity, final DoActionWrapper doAction) {
        checkNotNullConfiguration();
        checkPermission(activity, doAction, new MicrophoneChecker());
    }

    public static void checkPhone(final Activity activity, final DoActionWrapper doAction) {
        checkNotNullConfiguration();
        checkPermission(activity, doAction, new PhoneCheck());
    }

    public static void checkSensors(final Activity activity, final DoActionWrapper doAction) {
        checkNotNullConfiguration();
        checkPermission(activity, doAction, new SensorsChecker());
    }

    public static void checkSms(final Fragment fragment, final DoActionWrapper doAction) {
        checkSms(fragment.getActivity(), doAction);

    }

    public static void checkSms(final Activity activity, final DoActionWrapper doAction) {
        checkNotNullConfiguration();
        checkPermission(activity, doAction, new SMSChecker());
    }

    public static void checkStorage(final Activity activity, final DoActionWrapper doAction) {
        checkNotNullConfiguration();
        checkPermission(activity, doAction, new StorageChecker());
    }

    private static void checkPermission(final Activity activity, final DoActionWrapper doAction, final IChecker iChecker) {
        MissPermission.with(activity)
                .addPermissions(iChecker.getPermissions())
                .checkPermission(new PermissionRequest.PermissionListener() {
                    @Override
                    public int onChecked(PermissionRequest request) {
                        missHelperConfiguration.getAction().checkedAction(request.getContext(), request, Collections.singleton(iChecker));
                        return MissPermission.WAIT_STEP;
                    }

                    @Override
                    public void onDenied(PermissionRequest request) {
                        missHelperConfiguration.getAction().deniedAction(request.getContext(), request);
                        doAction.onFailure(request.getContext());
                    }

                    @Override
                    public void onSuccess(PermissionRequest request) {
                        if (iChecker.isCheckEnable(activity, missHelperConfiguration)) {
                            doAction.onSuccess(activity);
                        } else {
                            missHelperConfiguration.getAction().deniedAction(activity, request);
                            doAction.onFailure(request.getContext());
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

    public static void checkMorePermissions(final Activity activity, final DoActionWrapper doAction, int... permissionType) {
        checkNotNullConfiguration();
        MissPermission.Builder builder = MissPermission.with(activity);
        final Set<IChecker> checkers = new HashSet<>();
        IChecker iChecker = null;
        for (int type : permissionType) {
            switch (type) {
                case PermissionType.CALENDAR:
                    iChecker = new CalendarChecker();
                    break;
                case PermissionType.CALL_LOG:
                    iChecker = new CallLogChecker();
                    break;
                case PermissionType.CAMERA:
                    iChecker = new CameraChecker();
                    break;
                case PermissionType.CONTACTS:
                    iChecker = new ContactsChecker();
                    break;
                case PermissionType.LOCATION:
                    iChecker = new LocationChecker();
                    break;
                case PermissionType.RECORD_AUDIO:
                    iChecker = new MicrophoneChecker();
                    break;
                case PermissionType.PHONE:
                    iChecker = new PhoneCheck();
                    break;
                case PermissionType.SENSORS:
                    iChecker = new SensorsChecker();
                    break;
                case PermissionType.SMS:
                    iChecker = new SMSChecker();
                    break;
                case PermissionType.STORAGE:
                    iChecker = new StorageChecker();
                    break;
            }
            if (iChecker != null) {
                checkers.add(iChecker);
                builder.addPermissions(iChecker.getPermissions());
            }
        }
        for (IChecker checker : checkers) {
            builder.addPermissions(checker.getPermissions());
        }
        builder.checkPermission(new PermissionRequest.PermissionListener() {
            @Override
            public int onChecked(PermissionRequest request) {
                missHelperConfiguration.getAction().checkedAction(request.getContext(), request, checkers);
                return MissPermission.WAIT_STEP;
            }

            @Override
            public void onDenied(PermissionRequest request) {
                missHelperConfiguration.getAction().deniedAction(request.getContext(), request);
                for (String item : request.getDeniedPermissionList()) {
                    Log.e("zyh", "onDenied" + item);
                }
                doAction.onFailure(request.getContext());
            }

            @Override
            public void onSuccess(PermissionRequest request) {
                Log.e("zyh", "onSuccess....isCheckEnable");
                Set<String> deniedPermissions = new HashSet<>();
                for (IChecker checker : checkers) {
                    if (!checker.isCheckEnable(activity, missHelperConfiguration)) {
                        deniedPermissions.addAll(Arrays.asList(checker.getPermissions()));
                    }
                }
                if (deniedPermissions.isEmpty()) {
                    doAction.onSuccess(activity);
                } else {
                    missHelperConfiguration.getAction().deniedAction(activity, request);
                    doAction.onFailure(request.getContext());
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


    public interface PermissionType {
        int CALENDAR = 100;
        int CALL_LOG = 200;
        int CAMERA = 300;
        int CONTACTS = 400;
        int LOCATION = 500;
        int RECORD_AUDIO = 600;
        int PHONE = 700;
        int SENSORS = 800;
        int SMS = 900;
        int STORAGE = 1000;
    }

}
