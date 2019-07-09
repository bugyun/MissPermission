package vip.ruoyun.permission.helper.ui;

import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vip.ruoyun.permission.helper.R;
import vip.ruoyun.permission.helper.core.IChecker;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class PermissionAdapter extends BaseAdapter {
    private List<IChecker> mData;
    private int mTextColor;
    private int mFilterColor;

    public PermissionAdapter(List<IChecker> data) {
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IChecker item = mData.get(position);
        View view = View.inflate(parent.getContext(), R.layout.miss_permission_info_item, null);
        int blue = Color.blue(mFilterColor);
        int green = Color.green(mFilterColor);
        int red = Color.red(mFilterColor);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        float[] cm = new float[]{
                1, 0, 0, 0, red,// 红色值
                0, 1, 0, 0, green,// 绿色值
                0, 0, 1, 0, blue,// 蓝色值
                0, 0, 0, 1, 1 // 透明度
        };
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(cm);
        icon.setColorFilter(filter);
        TextView name = (TextView) view.findViewById(R.id.name);
        if (mTextColor != 0)
            name.setTextColor(mTextColor);
        icon.setImageResource(item.getPermissionIconRes());
        name.setText(item.getPermissionName());
        return view;
    }

    public void setTextColor(int itemTextColor) {
        mTextColor = itemTextColor;
        notifyDataSetChanged();
    }

    public void setFilterColor(int filterColor) {
        mFilterColor = filterColor;
        notifyDataSetChanged();
    }
}