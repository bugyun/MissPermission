package vip.ruoyun.permission.helper.test;


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

import java.util.List;

import vip.ruoyun.permission.core.PermissionRequest;
import vip.ruoyun.permission.helper.R;
import vip.ruoyun.permission.helper.ui.DialogUtil;
import vip.ruoyun.permission.helper.ui.MissPermissionView;

public class TDefaultAction implements IAction {

    private Dialog mDialog;

    @Override
    public void checkedAction(final PermissionRequest request,List<PermissionGroup> permissionGroups) {
        if (request.getDeniedPermissionList().size() == 0) {
            request.next();
            return;
        }

        mDialog = new Dialog(request.getContext());
        MissPermissionView contentView = new MissPermissionView(request.getContext());
//        List<IChecker> permissionItems = new ArrayList<>(permissionGroups);
//        contentView.setGridViewColum(permissionItems.size() < 3 ? permissionItems.size() : 3);
//        contentView.setGridViewAdapter(new PermissionAdapter(permissionItems));
        contentView.setTitle("亲爱的上帝");
        contentView.setMsg("为了保护世界的和平，开启这些权限吧！\n你我一起拯救世界");
        //这里没有使用RecyclerView，可以少引入一个库
        //用户没有设置，使用默认绿色主题
        int mStyleId = R.style.PermissionDefaultNormalStyle;
        int mFilterColor = request.getContext().getResources().getColor(R.color.MissHelperColorGreen);
        contentView.setStyleId(mStyleId);
        contentView.setFilterColor(mFilterColor);
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
