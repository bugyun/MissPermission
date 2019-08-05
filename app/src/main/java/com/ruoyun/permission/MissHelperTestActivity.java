package com.ruoyun.permission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MissHelperTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miss_helper_test);
    }


    private void test() {
//        ArrayList<Item> permissions = new ArrayList<>();
//        permissions.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_memory))
//        MissPermissionHelper.with(this)
//                .showprompt(true)//是否显示提示
//                .title("亲爱的上帝")
//                .msg("为了保护世界的和平，开启这些权限吧！\n你我一起拯救世界！")
//                .filterColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, getTheme()))//图标的颜色
//                .style(R.style.PermissionBlueStyle)
//                .ischeck(true)//是否要进行后续的检查权限
//                .permission("权限", "权限名称", "图片")
//                .permission("权限", "权限名称", "图片")
//                .permissions(permissions)
//                .listener(new MissPermissionHelper.DoActionWrapper() {
//                    @Override
//                    public void onSuccess(Context context) {
//                        Log.e("zyh", "onSuccess");
//                    }
//
//                    @Override
//                    public void onFailure(Context context) {
//                        Log.e("zyh", "onFailure");
//                    }
//                })
//                .go();
    }
}