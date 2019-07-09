package vip.ruoyun.permission.helper.check;

import android.Manifest;
import android.content.Context;

import vip.ruoyun.permission.helper.R;
import vip.ruoyun.permission.helper.core.IChecker;
import vip.ruoyun.permission.helper.core.IRomStrategy;
import vip.ruoyun.permission.helper.core.MissHelperConfiguration;

/**
 * Created by ruoyun on 2019-06-25.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class ContactsChecker implements IChecker {

    public static final String PERMISSION_NAME = "日历";

    static final int PERMISSION_ICONRES = R.drawable.miss_permission_ic_calendar;


    private IRomStrategy iRomStrategy;

    public ContactsChecker(IRomStrategy iRomStrategy) {
        this.iRomStrategy = iRomStrategy;
    }

    public static final String[] NEED_PERMISSION = {
            Manifest.permission.READ_CONTACTS,//
            Manifest.permission.WRITE_CONTACTS,//
            Manifest.permission.GET_ACCOUNTS,//
    };

    @Override
    public boolean isCheckEnable(Context context, MissHelperConfiguration configuration) {
        return true;
    }

    @Override
    public String getPermissionName() {
        return "联系人";
    }

    @Override
    public int getPermissionIconRes() {
        return R.drawable.miss_permission_ic_contacts;
    }

    @Override
    public String[] getPermissions() {
        return NEED_PERMISSION;
    }
}
