package vip.ruoyun.permission.pro;


import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.view.View;
import java.util.HashSet;
import java.util.Set;
import vip.ruoyun.helper.avoid.AvoidOnResultHelper;
import vip.ruoyun.permission.pro.ui.AgainRequestDialog;
import vip.ruoyun.permission.pro.ui.AlwaysDeniedDialog;
import vip.ruoyun.permission.pro.ui.RequestPromptDialog;


public class DefaultAction implements IAction {

    private RequestPromptDialog requestPromptDialog;
    private AlwaysDeniedDialog alwaysDeniedDialog;
    private AgainRequestDialog againRequestDialog;

    @Override
    public void checkedAction(final PermissionRequest request, Set<PermissionGroup> permissionGroups) {
        if (request.getDeniedPermissionList().size() == 0) {
            request.next();
            return;
        }
        if (requestPromptDialog == null) {
            requestPromptDialog = RequestPromptDialog.getRequestPromptDialog(request.getContext());
            requestPromptDialog.setTitleAndMsg(request.getTitle(), request.getMsg());
            requestPromptDialog.setBtnOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (requestPromptDialog != null && requestPromptDialog.isShowing()) {
                        requestPromptDialog.dismiss();
                    }
                    request.next();
                }
            });
        }
        requestPromptDialog.setPermissionGroups(permissionGroups);
        requestPromptDialog.setStyleId(request.getStyleResId());
        requestPromptDialog.show();
    }

    @Override
    public void deniedAction(final PermissionRequest request) {
        StringBuilder sBuilder = new StringBuilder();
        Set<PermissionGroup> permissionGroups = new HashSet<>();
        for (String deniedPermission : request.getDeniedPermissionList()) {
            PermissionGroup permissionGroup = PermissionGroup.permissionGroupHashMap.get(deniedPermission);
            if (permissionGroup != null) {
                permissionGroups.add(permissionGroup);
            }
        }
        for (PermissionGroup permissionGroup : permissionGroups) {
            sBuilder.append(permissionGroup.permissionName);
            sBuilder.append(",");
        }
        if (sBuilder.length() > 0) {
            sBuilder.deleteCharAt(sBuilder.length() - 1);
        }
        if (request.isAlwaysDenied()) {
            if (alwaysDeniedDialog == null) {
                alwaysDeniedDialog = AlwaysDeniedDialog.getAlwaysDeniedDialog(request.getContext());
                alwaysDeniedDialog.setStyleId(request.getStyleResId());
                alwaysDeniedDialog.setSettingClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (alwaysDeniedDialog != null && alwaysDeniedDialog.isShowing()) {
                            alwaysDeniedDialog.dismiss();
                        }
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + v.getContext().getPackageName()));
                        AvoidOnResultHelper.startActivityForResult(request.getContext(), intent, DefaultAction.this);
                    }
                });
            }
            alwaysDeniedDialog.setTitleAndMsg("温馨提示", "获取" + sBuilder.toString() + "权限被禁用" + "\n请在 设置-应用管理-权限管理 (将权限打开)");
            alwaysDeniedDialog.show();
        } else {
            if (againRequestDialog == null) {
                againRequestDialog = AgainRequestDialog.getAlwaysDeniedDialog(request.getContext());
                againRequestDialog.setStyleId(request.getStyleResId());
                againRequestDialog.setSettingClickListener("验证权限", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        request.requestPermissionsAgain();
                        if (againRequestDialog != null && againRequestDialog.isShowing()) {
                            againRequestDialog.dismiss();
                        }
                    }
                });
                againRequestDialog.setTitleAndMsg("温馨提示", "我们需要这些权限才能正常使用该功能");
            }
            againRequestDialog.show();
        }
    }

    @Override
    public void onActivityResult(int resultCode, Intent data) {

    }
}
