package vip.ruoyun.permission.helper;


import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import java.util.Set;

import vip.ruoyun.helper.avoid.AvoidOnResultHelper;
import vip.ruoyun.permission.helper.ui.AgainRequestDialog;
import vip.ruoyun.permission.helper.ui.AlwaysDeniedDialog;
import vip.ruoyun.permission.helper.ui.RequestPromptDialog;

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
            requestPromptDialog.setStyleId(request.getStyleResId());
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
        requestPromptDialog.setFilterColor(request.getFilterColor());
        requestPromptDialog.show();
    }

    @Override
    public void deniedAction(final PermissionRequest request) {
        StringBuilder sBuilder = new StringBuilder();
        for (String deniedPermission : request.getDeniedPermissionList()) {
            if (deniedPermission.equals(Manifest.permission.WRITE_CONTACTS)) {
                sBuilder.append("联系人");
                sBuilder.append(",");
            }
            if (deniedPermission.equals(Manifest.permission.READ_SMS)) {
                sBuilder.append("短信");
                sBuilder.append(",");
            }
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
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + v.getContext().getPackageName()));
                        AvoidOnResultHelper.startActivityForResult(request.getContext(), intent, new AvoidOnResultHelper.ActivityCallback() {
                            @Override
                            public void onActivityResult(int resultCode, Intent data) {
                                Log.e("DialogUtil", "返回值为" + resultCode);
                            }
                        });
                    }
                });
            }
            alwaysDeniedDialog.setTitleAndMsg("微信提示", "获取" + sBuilder.toString() + "权限被禁用" + "请在 设置-应用管理-权限管理 (将权限打开)");
            alwaysDeniedDialog.show();
        } else {
            if (againRequestDialog == null) {
                againRequestDialog = AgainRequestDialog.getAlwaysDeniedDialog(request.getContext());
                againRequestDialog.setStyleId(request.getStyleResId());
                againRequestDialog.setSettingClickListener("验证权限", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        request.requestPermissionsAgain();
                    }
                });
                againRequestDialog.setTitleAndMsg("微信提示", "我们需要这些权限才能正常使用该功能");
            }
            againRequestDialog.show();
        }
    }


}
