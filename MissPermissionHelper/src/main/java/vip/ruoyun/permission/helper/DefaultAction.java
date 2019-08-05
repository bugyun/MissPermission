package vip.ruoyun.permission.helper;


import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.Set;

import vip.ruoyun.permission.helper.ui.DialogUtil;
import vip.ruoyun.permission.helper.ui.MissPermissionView;
import vip.ruoyun.permission.helper.ui.PermissionAdapter;

public class DefaultAction implements IAction {

    private Dialog mDialog;

    @Override
    public void checkedAction(final PermissionRequest request, Set<PermissionGroup> permissionGroups) {
        if (request.getDeniedPermissionList().size() == 0) {
            request.next();
            return;
        }
        mDialog = new Dialog(request.getContext());
        MissPermissionView contentView = new MissPermissionView(request.getContext());
        contentView.setGridViewColum(permissionGroups.size() < 3 ? permissionGroups.size() : 3);
        contentView.setGridViewAdapter(new PermissionAdapter(new ArrayList<>(permissionGroups)));
//        if (request.getFilterColor() != 0) {
//            int mFilterColor = request.getContext().getResources().getColor(request.getFilterColor());
//            contentView.setFilterColor(mFilterColor);
//        }
        contentView.setTitle(request.getTitle());
        contentView.setMsg(request.getMsg());
        contentView.setStyleId(request.getStyleResId());
        contentView.setBtnOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                }
                request.next();
            }
        });
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(contentView);

        mDialog.setCanceledOnTouchOutside(false);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
        mDialog.show();
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
        //Toast.makeText(context, "获取" + sBuilder.toString() + "权限失败", Toast.LENGTH_SHORT).show();
        if (request.isAlwaysDenied()) {
            DialogUtil.showPermissionManagerDialog(request.getContext(), sBuilder.toString());
        } else {
            new AlertDialog.Builder(request.getContext()).setTitle("温馨提示").setMessage("我们需要这些权限才能正常使用该功能").setNegativeButton("取消", null).setPositiveButton("验证权限", new DialogInterface.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    request.requestPermissionsAgain();
                }
            }).setCancelable(false).show();
        }
    }


}
