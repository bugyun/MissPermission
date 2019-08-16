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

public class AgainRequestDialog extends Dialog {
    private MissPermissionAlwaysDeniedView contentView;

    public AgainRequestDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public AgainRequestDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    public AgainRequestDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        contentView = new MissPermissionAlwaysDeniedView(getContext());
        contentView.setCancelClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowing()) {
                    dismiss();
                }
            }
        });
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(contentView);
        setCanceledOnTouchOutside(false);
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
    }

    public void setSettingClickListener(String name, View.OnClickListener listener) {
        contentView.setBtSettingName(name);
        contentView.setSettingClickListener(listener);
    }

    public void setStyleId(int styleId) {
        contentView.setStyleId(styleId);
    }

    public void setTitleAndMsg(String title, String msg) {
        contentView.setTitle(title);
        contentView.setMsg(msg);
    }

    public MissPermissionAlwaysDeniedView getContentView() {
        return contentView;
    }


    public static AgainRequestDialog getAlwaysDeniedDialog(Context context) {
        AgainRequestDialog alwaysDialog = new AgainRequestDialog(context);
        return alwaysDialog;
    }


}
