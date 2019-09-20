package vip.ruoyun.permission.pro;

import android.Manifest;
import android.os.Build;
import java.util.HashMap;
import vip.ruoyun.permission.pro.check.CalendarChecker;
import vip.ruoyun.permission.pro.check.CameraChecker;
import vip.ruoyun.permission.pro.check.ContactsChecker;
import vip.ruoyun.permission.pro.check.LocationChecker;
import vip.ruoyun.permission.pro.check.MicrophoneChecker;
import vip.ruoyun.permission.pro.check.PhoneChecker;
import vip.ruoyun.permission.pro.check.SMSChecker;
import vip.ruoyun.permission.pro.check.SensorsChecker;
import vip.ruoyun.permission.pro.check.StorageChecker;

public class PermissionGroup {

    public final String permissionName;
    public final int permissionIconRes;

    public PermissionGroup(String permissionName, int permissionIconRes) {
        this.permissionName = permissionName;
        this.permissionIconRes = permissionIconRes;
    }

    static HashMap<String, PermissionGroup> permissionGroupHashMap = new HashMap<>(35);

    /**
     * 添加图标
     */
    static {
        //CalendarChecker
        PermissionGroup calendarGroup = new PermissionGroup(CalendarChecker.PERMISSION_NAME, CalendarChecker.PERMISSION_ICON_RES);
        permissionGroupHashMap.put(Manifest.permission.READ_CALENDAR, calendarGroup);
        permissionGroupHashMap.put(Manifest.permission.WRITE_CALENDAR, calendarGroup);

        //PhoneChecker
        PermissionGroup phoneGroup = new PermissionGroup(PhoneChecker.PERMISSION_NAME, PhoneChecker.PERMISSION_ICON_RES);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            permissionGroupHashMap.put(Manifest.permission.READ_CALL_LOG, phoneGroup);
            permissionGroupHashMap.put(Manifest.permission.WRITE_CALL_LOG, phoneGroup);
        }
        permissionGroupHashMap.put(Manifest.permission.PROCESS_OUTGOING_CALLS, phoneGroup);
        permissionGroupHashMap.put(Manifest.permission.READ_PHONE_STATE, phoneGroup);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            permissionGroupHashMap.put(Manifest.permission.READ_PHONE_NUMBERS, phoneGroup);
            permissionGroupHashMap.put(Manifest.permission.ANSWER_PHONE_CALLS, phoneGroup);
        }
        permissionGroupHashMap.put(Manifest.permission.CALL_PHONE, phoneGroup);
        permissionGroupHashMap.put(Manifest.permission.ADD_VOICEMAIL, phoneGroup);

        //CameraChecker
        PermissionGroup cameraGroup = new PermissionGroup(CameraChecker.PERMISSION_NAME, CameraChecker.PERMISSION_ICON_RES);
        permissionGroupHashMap.put(Manifest.permission.CAMERA, cameraGroup);

        //ContactsChecker
        PermissionGroup contactsGroup = new PermissionGroup(ContactsChecker.PERMISSION_NAME, ContactsChecker.PERMISSION_ICON_RES);
        permissionGroupHashMap.put(Manifest.permission.READ_CONTACTS, contactsGroup);
        permissionGroupHashMap.put(Manifest.permission.WRITE_CONTACTS, contactsGroup);
        permissionGroupHashMap.put(Manifest.permission.GET_ACCOUNTS, contactsGroup);

        //LocationChecker
        PermissionGroup locationGroup = new PermissionGroup(LocationChecker.PERMISSION_NAME, LocationChecker.PERMISSION_ICON_RES);
        permissionGroupHashMap.put(Manifest.permission.ACCESS_FINE_LOCATION, locationGroup);
        permissionGroupHashMap.put(Manifest.permission.ACCESS_COARSE_LOCATION, locationGroup);
        permissionGroupHashMap.put("android.permission.ACCESS_BACKGROUND_LOCATION", locationGroup);

        //MicrophoneChecker
        PermissionGroup microphoneGroup = new PermissionGroup(MicrophoneChecker.PERMISSION_NAME, MicrophoneChecker.PERMISSION_ICON_RES);
        permissionGroupHashMap.put(Manifest.permission.RECORD_AUDIO, microphoneGroup);

        //SensorsChecker
        PermissionGroup sensorsGroup = new PermissionGroup(SensorsChecker.PERMISSION_NAME, SensorsChecker.PERMISSION_ICON_RES);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            permissionGroupHashMap.put(Manifest.permission.BODY_SENSORS, sensorsGroup);
        }

        //SMSChecker
        PermissionGroup smsGroup = new PermissionGroup(SMSChecker.PERMISSION_NAME, SMSChecker.PERMISSION_ICON_RES);
        permissionGroupHashMap.put(Manifest.permission.SEND_SMS, smsGroup);
        permissionGroupHashMap.put(Manifest.permission.RECEIVE_SMS, smsGroup);
        permissionGroupHashMap.put(Manifest.permission.READ_SMS, smsGroup);
        permissionGroupHashMap.put(Manifest.permission.RECEIVE_WAP_PUSH, smsGroup);
        permissionGroupHashMap.put(Manifest.permission.RECEIVE_MMS, smsGroup);

        //StorageChecker
        PermissionGroup storageGroup = new PermissionGroup(StorageChecker.PERMISSION_NAME, StorageChecker.PERMISSION_ICON_RES);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            permissionGroupHashMap.put(Manifest.permission.READ_EXTERNAL_STORAGE, storageGroup);
        }
        permissionGroupHashMap.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, storageGroup);
    }


}
