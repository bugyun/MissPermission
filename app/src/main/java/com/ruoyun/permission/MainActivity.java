package com.ruoyun.permission;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.ruoyun.dpermission.DPermission;
import com.ruoyun.dpermission.PermissionException;
import com.ruoyun.dpermission.PermissionRequest;
import com.ruoyun.dpermission.RequestCode;

import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionAdapter;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;
import me.weyye.hipermission.PermissionView;

public class MainActivity extends AppCompatActivity {


    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 2000;
    private static final String TAG = "zyh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //测试

        String manufacturer = android.os.Build.MANUFACTURER;
        Log.e("zyh", "制造商" + manufacturer);

        testDPermission();

        //        testHiPermisson();
    }

    private void testHiPermisson() {
        List<PermissionItem> permissionItems = new ArrayList<PermissionItem>();
        permissionItems.add(new PermissionItem(Manifest.permission.CAMERA));
        permissionItems.add(new PermissionItem(Manifest.permission.SEND_SMS));
        permissionItems.add(new PermissionItem(Manifest.permission.RECEIVE_SMS));
        permissionItems.add(new PermissionItem(Manifest.permission.READ_SMS));
        permissionItems.add(new PermissionItem(Manifest.permission.READ_CONTACTS));
        permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "定位", R.drawable.permission_ic_location));
        HiPermission.create(MainActivity.this).permissions(permissionItems).checkMutiPermission(new PermissionCallback() {
            @Override
            public void onClose() {
                Log.i(TAG, "用户关闭权限申请");

            }

            @Override
            public void onFinish() {
                Log.i(TAG, "所有权限申请完成");

            }

            @Override
            public void onDeny(String permission, int position) {
                Log.i(TAG, "onDeny");
            }

            @Override
            public void onGuarantee(String permission, int position) {
                Log.i(TAG, "onGuarantee");
            }
        });


    }

    Dialog mDialog;

    private void testDPermission() {
        DPermission.getInstance()//
                .with()//
                .setPermissionListener(new PermissionRequest.PermissionListener() {
                    @Override
                    public int onChecked(boolean isGreater, List<String> agreeList, List<String> rejectList, final PermissionRequest request) {
                        Log.i("zyh", "onChecked: rejectList:" + rejectList.size() + "agree:" + agreeList.size());
                        PermissionView contentView = new PermissionView(MainActivity.this);
                        contentView.setGridViewColum(3);
                        contentView.setTitle("标题");
                        contentView.setMsg("内容");
                        //这里没有使用RecyclerView，可以少引入一个库
                        List<PermissionItem> permissionItems = new ArrayList<>();
                        permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
                        permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "定位", R.drawable.permission_ic_location));
                        permissionItems.add(new PermissionItem(Manifest.permission.READ_CONTACTS, "通讯录", R.drawable.permission_ic_contacts));
                        contentView.setGridViewAdapter(new PermissionAdapter(permissionItems));
                        //用户没有设置，使用默认绿色主题
                        int mStyleId = R.style.PermissionDefaultNormalStyle;
                        int mFilterColor = getResources().getColor(R.color.permissionColorGreen);

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
                        mDialog = new Dialog(MainActivity.this);
                        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        mDialog.setContentView(contentView);

                        mDialog.setCanceledOnTouchOutside(false);
                        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                dialog.dismiss();
                                request.stop();
                            }
                        });
                        mDialog.show();
                        return DPermission.WAIT_STEP;
                    }

                    @Override
                    public void onDenied(final List<String> deniedPermissions, boolean alwaysDenied, final PermissionRequest request) {
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
                            DialogUtil.showPermissionManagerDialog(MainActivity.this, sBuilder.toString());
                        } else {
                            new AlertDialog.Builder(MainActivity.this).setTitle("温馨提示").setMessage("我们需要这些权限才能正常使用该功能").setNegativeButton("取消", null).setPositiveButton("验证权限", new DialogInterface.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.M)
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    request.requestPermissionsAgain(deniedPermissions);
                                }
                            }).setCancelable(false).show();
                        }
                    }

                    @Override
                    public void onSuccess() {
                        Log.i("zyh", "成功");
                    }

                    @Override
                    public void onFailure(PermissionException exception) {
                        Log.i("zyh", exception.getMessage());
                    }
                })//
                .setRequestCode(RequestCode.MORE)//
                .addPermission(Manifest.permission.CAMERA)//
                .addPermission(Manifest.permission.SEND_SMS)//
                .addPermission(Manifest.permission.RECEIVE_SMS)//
                .addPermission(Manifest.permission.READ_SMS)//
                .addPermission(Manifest.permission.READ_CONTACTS)//
                .addPermission(Manifest.permission.ACCESS_FINE_LOCATION)//
                .create()//
                .start(this);
    }


    public void test(String permission) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {//如果小于23的话
            return;
        }
        if (TextUtils.isEmpty(permission)) {
            return;
        }

        // 检测是否有权限
        int permissionCheck = ContextCompat.checkSelfPermission(this, permission);


        /**
         * 如果应用不具有此权限：PackageManager.PERMISSION_DENIED
         * 如果应用具有此权限：PackageManager.PERMISSION_GRANTED
         */
        if (PackageManager.PERMISSION_GRANTED == permissionCheck) {//有此权限

        }


        if (shouldShowRequestPermissionRationale(permission)) {


        }


        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            //如果用户在过去拒绝了权限请求，并在权限请求系统对话框中选择了 Don't ask again 选项，此方法将返回 false。如果设备规范禁止应用具有该权限，此方法也会返回 false。
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }
    //requestPermissions(android.app.Activity, String[], int)

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        DPermission.getInstance().onRequestPermissionsResult(requestCode, permissions, grantResults);
        //        switch (requestCode) {
        //            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
        //                // If request is cancelled, the result arrays are empty.
        //                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        //                    //用户点击了接受，可以进行相应处理
        //                    // permission was granted, yay! Do the
        //                    // contacts-related task you need to do.
        //
        //                } else {
        //                    //用户点击了拒绝，可以进行相应处理
        //                    // permission denied, boo! Disable the
        //                    // functionality that depends on this permission.
        //                }
        //                return;
        //            }
        //
        //            // other 'case' lines to check for other
        //            // permissions this app might request
        //        }
    }
}
