package vip.ruoyun.permission.helper.core;

import android.content.Context;

import java.util.List;
import java.util.Set;

import vip.ruoyun.permission.core.PermissionRequest;

public interface BaseAction {

    void checkedAction(Context context, Set<String> rejectList, PermissionRequest request, List<IChecker> iCheckers);

    void deniedAction(Context context, Set<String> deniedPermissions, boolean alwaysDenied, PermissionRequest request);
}