package vip.ruoyun.permission.helper.core;

import android.content.Context;

import java.util.List;

import vip.ruoyun.permission.core.PermissionRequest;

public interface BaseAction {

    void checkedAction(Context context, List<String> rejectList, PermissionRequest request);

    void deniedAction(Context context, List<String> deniedPermissions, boolean alwaysDenied, PermissionRequest request);
}
