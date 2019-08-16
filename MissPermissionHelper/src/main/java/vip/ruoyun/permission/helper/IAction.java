package vip.ruoyun.permission.helper;

import java.util.Set;

import vip.ruoyun.helper.avoid.AvoidOnResultHelper;


public interface IAction extends AvoidOnResultHelper.ActivityCallback {

    /**
     * 检测权限后,触发的事件
     * @param request 权限请求
     * @param permissionGroups 需要请求的权限集合
     */
    void checkedAction(PermissionRequest request, Set<PermissionGroup> permissionGroups);

    /**
     * 拒绝触发的事件
     * @param request 权限请求
     */
    void deniedAction(PermissionRequest request);
}