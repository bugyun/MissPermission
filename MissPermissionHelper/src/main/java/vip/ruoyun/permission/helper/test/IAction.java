package vip.ruoyun.permission.helper.test;

import java.util.List;

import vip.ruoyun.permission.core.PermissionRequest;

public interface IAction {

    void checkedAction(PermissionRequest request, List<PermissionGroup> permissionGroups);

    void deniedAction(PermissionRequest request);
}