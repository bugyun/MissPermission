package vip.ruoyun.permission.helper.check;

import android.Manifest;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;

import vip.ruoyun.permission.helper.R;
import vip.ruoyun.permission.helper.core.IChecker;
import vip.ruoyun.permission.helper.core.MissHelperConfiguration;

/**
 * Created by ruoyun on 2019-06-25.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class SMSChecker implements IChecker {

    public static final String PERMISSION_NAME = "日历";

    static final int PERMISSION_ICONRES = R.drawable.miss_permission_ic_calendar;

    public static final String[] NEED_PERMISSION = {
            Manifest.permission.SEND_SMS,//
            Manifest.permission.RECEIVE_SMS,//
            Manifest.permission.READ_SMS,//
            Manifest.permission.RECEIVE_WAP_PUSH,//
            Manifest.permission.RECEIVE_MMS,//
    };

    /**
     * 有的手机 返回是有权限，不弹权限框，但是需要在获取的时候才能 获取这个权限，要事先检测一次
     * <p>
     * 华为手机，会弹出请求权限的弹框
     *
     * @param context
     * @param configuration
     * @return
     */
    @Override
    public boolean isCheckEnable(Context context, MissHelperConfiguration configuration) {
        if (!configuration.getRomStrategy().isNeedCheck()) {
            return true;
        }
        Cursor cursor = context.getContentResolver().query(Uri.parse("content://sms/"), null, null,
                null, null);
        if (cursor != null) {
            if (isNumberIndexInfoIsNull(cursor, cursor.getColumnIndex(Telephony.Sms.DATE))) {
                cursor.close();
                return false;
            }
            cursor.close();
            return true;
        } else {
            return false;
        }
    }


    private static boolean isNumberIndexInfoIsNull(Cursor cursor, int numberIndex) {
        if (cursor.getCount() > 0) {
            if (cursor.moveToNext()) {
                Log.e("zyh", cursor.getString(numberIndex));
                return TextUtils.isEmpty(cursor.getString(numberIndex));
            }
            return false;
        } else {
            return false;
        }
    }

    @Override
    public String getPermissionName() {
        return "信息";
    }

    @Override
    public int getPermissionIconRes() {
        return R.drawable.miss_permission_ic_sms;
    }

    @Override
    public String[] getPermissions() {
        return NEED_PERMISSION;
    }
}
