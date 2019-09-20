package vip.ruoyun.permission.pro.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import vip.ruoyun.permission.pro.R;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class MissPermissionAlwaysDeniedView extends FrameLayout {

    private TextView mTvTitle;
    private TextView mTvDesc;
    private LinearLayout mLlRoot;
    private Button mBtSetting;
    private Button mBtCancel;

    public MissPermissionAlwaysDeniedView(Context context) {
        this(context, null);
    }

    public MissPermissionAlwaysDeniedView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MissPermissionAlwaysDeniedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View permissionView = View.inflate(getContext(), R.layout.miss_permission_dialog_always_denied_permission, this);
        mTvTitle = (TextView) permissionView.findViewById(R.id.tvTitle);
        mLlRoot = (LinearLayout) permissionView.findViewById(R.id.llRoot);
        mTvDesc = (TextView) permissionView.findViewById(R.id.tvDesc);
        mBtSetting = (Button) permissionView.findViewById(R.id.mBtSetting);
        mBtCancel = (Button) permissionView.findViewById(R.id.mBtCancel);
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public void setMsg(String msg) {
        mTvDesc.setText(msg);
    }

    public void setCancelClickListener(OnClickListener listener) {
        mBtCancel.setOnClickListener(listener);
    }

    public void setSettingClickListener(OnClickListener listener) {
        mBtSetting.setOnClickListener(listener);
    }

    public void setBtSettingName(String name) {
        mBtSetting.setText(name);
    }

    @SuppressWarnings("ResourceType")
    public void setStyleId(int styleId) {
        if (styleId <= 0)
            return;
        int[] ints = {
                R.attr.MissPermissionMsgColor,
                R.attr.MissPermissionTitleColor,
                R.attr.MissPermissionItemTextColor,
                R.attr.MissPermissionButtonTextColor,
                R.attr.MissPermissionBackground,
                R.attr.MissPermissionButtonBackground,
                R.attr.MissPermissionBgFilterColor,
                R.attr.MissPermissionIconFilterColor
        };

        Resources.Theme theme = getResources().newTheme();
        theme.applyStyle(styleId, true);

        TypedArray typedArray = theme.obtainStyledAttributes(ints);
        int msgColor = typedArray.getColor(0, 0);
        int titleColor = typedArray.getColor(1, 0);
        int btnTextColor = typedArray.getColor(3, 0);
        Drawable background = typedArray.getDrawable(4);
        Drawable Btnbackground = typedArray.getDrawable(5);
        int bgFilterColor = typedArray.getColor(6, 0);

        if (titleColor != 0)
            mTvTitle.setTextColor(titleColor);
        if (background != null) {
            if (bgFilterColor != 0)
                background.setColorFilter(getColorFilter(bgFilterColor));
            mLlRoot.setBackgroundDrawable(background);
        }
        if (msgColor != 0)
            mTvDesc.setTextColor(msgColor);
        if (Btnbackground != null) {
            mBtSetting.setBackgroundDrawable(Btnbackground);
            mBtCancel.setBackgroundDrawable(Btnbackground);
        }
        if (btnTextColor != 0) {
            mBtSetting.setTextColor(btnTextColor);
            mBtCancel.setTextColor(btnTextColor);
        }
        typedArray.recycle();

    }

    private ColorFilter getColorFilter(int bgFilterColor) {
        int blue = Color.blue(bgFilterColor);
        int green = Color.green(bgFilterColor);
        int red = Color.red(bgFilterColor);
        float[] cm = new float[]{
                1, 0, 0, 0, red,// 红色值
                0, 1, 0, 0, green,// 绿色值
                0, 0, 1, 0, blue,// 蓝色值
                0, 0, 0, 1, 1 // 透明度
        };
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(cm);
        return filter;
    }
}
