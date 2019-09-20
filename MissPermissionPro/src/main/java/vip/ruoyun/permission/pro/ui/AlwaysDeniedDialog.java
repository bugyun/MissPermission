package vip.ruoyun.permission.pro.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;

public class AlwaysDeniedDialog extends Dialog {
    private MissPermissionAlwaysDeniedView contentView;

    public AlwaysDeniedDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public AlwaysDeniedDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    public AlwaysDeniedDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
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

    public void setSettingClickListener(View.OnClickListener listener) {
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

    public static AlwaysDeniedDialog getAlwaysDeniedDialog(Context context) {
        return new AlwaysDeniedDialog(context);
    }

}
