package com.ruoyun.permission;

import android.Manifest;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import vip.ruoyun.permission.pro.DefaultAction;
import vip.ruoyun.permission.pro.MissPermission;
import vip.ruoyun.permission.pro.PermissionRequest;
import vip.ruoyun.permission.pro.check.SMSChecker;
import vip.ruoyun.screen.ScreenHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    Button buttonCamera;

    Button buttonAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //测试
        buttonCamera = findViewById(R.id.buttonCamera);
        buttonAll = findViewById(R.id.buttonAll);
        buttonCamera.setOnClickListener(this);
        buttonAll.setOnClickListener(this);
        String manufacturer = android.os.Build.MANUFACTURER;
        Log.e("zyh", "制造商" + manufacturer);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCamera:
                if (MissPermission.check(this, SMSChecker.NEED_PERMISSION)) {
                    Log.e(TAG, "有权限");
                } else {
                    Log.e(TAG, "没有权限");
                }

                if (MissPermission.realCheck(this, Manifest.permission.READ_SMS)) {
                    Log.e(TAG, "有短信权限");

                } else {
                    Log.e(TAG, "没有短信权限");
                }

                MissPermission.with(this)
//                        .permission(Manifest.permission.SEND_SMS)//
//                        .permission(Manifest.permission.RECEIVE_SMS)//
//                        .permission(Manifest.permission.READ_SMS)//
                        .permission(Manifest.permission.ACCESS_FINE_LOCATION)//
                        .permission(Manifest.permission.CAMERA)//
                        .permission(Manifest.permission.READ_CONTACTS)//
                        .permission(Manifest.permission.WRITE_CALENDAR)//
//                        .permission(Manifest.permission.READ_CALL_LOG)//
                        .permission(Manifest.permission.READ_CONTACTS)//
                        .permission(Manifest.permission.RECORD_AUDIO)//
//                        .permission(Manifest.permission.BODY_SENSORS)//
                        .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)//
                        .action(new DefaultAction() {
                            @Override
                            public void onActivityResult(int resultCode, Intent data) {

                            }
                        })
                        .msg("为了您正常使用应用,需要以下权限")
                        .title("亲爱的用户")
                        .prompt(true)
                        .style(R.style.MissPermissionDefaultNormalStyle)
                        .check(new PermissionRequest.PermissionListener() {
                            @Override
                            public void onSuccess(PermissionRequest request) {
                                Toast.makeText(MainActivity.this, "获取权限成功", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(PermissionRequest request) {
//                                Toast.makeText(MainActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
                                Log.e("zyh", "onFailure");
                            }
                        });

                break;
            case R.id.buttonAll:
                break;
        }
    }

    public Resources getResources() {
        return ScreenHelper.applyAdapt(super.getResources(), 375f, ScreenHelper.WIDTH_DP);
    }

}
