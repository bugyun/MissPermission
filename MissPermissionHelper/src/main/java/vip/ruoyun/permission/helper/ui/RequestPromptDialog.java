package vip.ruoyun.permission.helper.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.Set;

import vip.ruoyun.permission.helper.PermissionGroup;

public class RequestPromptDialog extends Dialog {

    private MissPermissionView contentView;

    public RequestPromptDialog(@NonNull Context context) {
        super(context);
        init();
    }


    public RequestPromptDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    public RequestPromptDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        contentView = new MissPermissionView(getContext());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(contentView);
        setCanceledOnTouchOutside(false);
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
    }

    public void setBtnOnClickListener(View.OnClickListener listener) {
        contentView.setBtnOnClickListener(listener);
    }

    public void setStyleId(int styleId) {
        contentView.setStyleId(styleId);
    }

    public void setTitleAndMsg(String title, String msg) {
        contentView.setTitle(title);
        contentView.setMsg(msg);
    }

    public void setFilterColor(int filterColor) {
        int mFilterColor = getContext().getResources().getColor(filterColor);
        contentView.setFilterColor(mFilterColor);
    }

    public void setPermissionGroups(Set<PermissionGroup> permissionGroups) {
        contentView.setGridViewColum(permissionGroups.size() < 3 ? permissionGroups.size() : 3);
        contentView.setGridViewAdapter(new PermissionAdapter(new ArrayList<>(permissionGroups)));
    }

    public MissPermissionView getContentView() {
        return contentView;
    }

    public static RequestPromptDialog getRequestPromptDialog(Context context) {
        return new RequestPromptDialog(context);
    }

}
