package vip.ruoyun.permission.helper.core;

import android.content.Context;

import java.util.Set;

import vip.ruoyun.permission.core.PermissionRequest;

public interface BaseAction {

    void checkedAction(Context context, PermissionRequest request, Set<IChecker> iCheckers);

    void deniedAction(Context context, PermissionRequest request);
}