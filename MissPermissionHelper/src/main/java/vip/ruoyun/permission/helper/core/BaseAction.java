package vip.ruoyun.permission.helper.core;

import java.util.Set;

import vip.ruoyun.permission.core.PermissionRequest;

public interface BaseAction {

    void checkedAction(PermissionRequest request, Set<IChecker> iCheckers);

    void deniedAction(PermissionRequest request);
}