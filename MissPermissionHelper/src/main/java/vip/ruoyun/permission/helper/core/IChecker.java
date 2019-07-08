package vip.ruoyun.permission.helper.core;

import android.content.Context;

public interface IChecker {

    String[] NEED_PERMISSION = {};

    boolean isCheckEnable(Context context, MissHelperConfiguration configuration);


}
