package vip.ruoyun.permission.helper;

import java.util.Set;

import vip.ruoyun.helper.avoid.AvoidOnResultHelper;


public interface IAction extends AvoidOnResultHelper.ActivityCallback {

    void checkedAction(PermissionRequest request, Set<PermissionGroup> permissionGroups);

    void deniedAction(PermissionRequest request);
}