package vip.ruoyun.permission.helper;

import java.util.Set;


public interface IAction {

    void checkedAction(PermissionRequest request, Set<PermissionGroup> permissionGroups);

    void deniedAction(PermissionRequest request);
}