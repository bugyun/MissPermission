package vip.ruoyun.permission.helper.test;

public class PermissionGroup {

    private String[] permission;
    private String permissionName;
    private int PermissionIconRes;

    public String[] getPermission() {
        return permission;
    }

    public void setPermission(String[] permission) {
        this.permission = permission;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public int getPermissionIconRes() {
        return PermissionIconRes;
    }

    public void setPermissionIconRes(int permissionIconRes) {
        PermissionIconRes = permissionIconRes;
    }
}
