package vip.ruoyun.permission.helper.core;


import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;

import java.util.List;

import vip.ruoyun.permission.core.PermissionRequest;

public class DefaultAction implements BaseAction {

    private Dialog mDialog;

    @Override
    public void checkedAction(Context context, List<String> rejectList, final PermissionRequest request) {
        mDialog = new Dialog(context);
//        PermissionView contentView = new PermissionView(context);
//        contentView.setGridViewColum(3);
//        contentView.setTitle("标题");
//        contentView.setMsg("内容");
//        //这里没有使用RecyclerView，可以少引入一个库
//        List<PermissionItem> permissionItems = new ArrayList<>();
//        permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
//        permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "定位", R.drawable.permission_ic_location));
//        permissionItems.add(new PermissionItem(Manifest.permission.READ_CONTACTS, "通讯录", R.drawable.permission_ic_contacts));
//        contentView.setGridViewAdapter(new PermissionAdapter(permissionItems));
//        //用户没有设置，使用默认绿色主题
//        int mStyleId = R.style.PermissionDefaultNormalStyle;
//        int mFilterColor = getResources().getColor(R.color.permissionColorGreen);
//
//        contentView.setStyleId(mStyleId);
//        contentView.setFilterColor(mFilterColor);
//        contentView.setBtnOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mDialog != null && mDialog.isShowing()) {
//                    mDialog.dismiss();
//                }
//                request.next();
//            }
//        });
//        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        mDialog.setContentView(contentView);

        mDialog.setCanceledOnTouchOutside(false);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
//                                request.stop();
            }
        });
        mDialog.show();
    }

    @Override
    public void deniedAction(Context context, final List<String> deniedPermissions, boolean alwaysDenied, final PermissionRequest request) {
        StringBuilder sBuilder = new StringBuilder();
        for (String deniedPermission : deniedPermissions) {
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
        if (alwaysDenied) {
            DialogUtil.showPermissionManagerDialog(context, sBuilder.toString());
        } else {
            new AlertDialog.Builder(context).setTitle("温馨提示").setMessage("我们需要这些权限才能正常使用该功能").setNegativeButton("取消", null).setPositiveButton("验证权限", new DialogInterface.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    request.requestPermissionsAgain(deniedPermissions);
                }
            }).setCancelable(false).show();
        }
    }
}
