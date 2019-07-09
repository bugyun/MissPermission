package vip.ruoyun.permission.helper.core;

import android.content.Context;

public interface IChecker {

    boolean isCheckEnable(Context context, MissHelperConfiguration configuration);

    String getPermissionName();

    int getPermissionIconRes();

    String[] getPermissions();

}
