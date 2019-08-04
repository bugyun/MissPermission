package vip.ruoyun.permission.helper.test;

public class PermissionItem {

    public String PermissionName;
    public String Permission;
    public int PermissionIconRes;
    public int permissGroupId;

    public PermissionItem(String permission, String permissionName, int permissionIconRes) {
        Permission = permission;
        PermissionName = permissionName;
        PermissionIconRes = permissionIconRes;
    }

    public PermissionItem(String permission) {
        Permission = permission;
    }
}
